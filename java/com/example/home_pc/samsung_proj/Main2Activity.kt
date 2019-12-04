package com.example.home_pc.samsung_proj

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity


import android.speech.RecognizerIntent
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.home_pc.samsung_proj.Main2Activity.a.curfilename
import com.example.home_pc.samsung_proj.Main2Activity.a.editval
import com.example.home_pc.samsung_proj.MainActivity.Companion.AppUser
import com.example.home_pc.samsung_proj.MainActivity.Companion.isnew
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference




class Main2Activity : AppCompatActivity() {
    object a{
        @JvmStatic
        lateinit var curfilename:String
        @JvmStatic
         var editval=""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main2)
        var txt=findViewById(R.id.edittextblanc)as EditText
        if (!isnew){
        txt.setText( editval)
        }
        val btnvoice=findViewById<Button>(R.id.btnvoice)
        val btnsave=findViewById<Button>(R.id.btnsave)

        btnvoice.setOnClickListener{

            finish()
        }
        btnsave.setOnClickListener {

            val ref = FirebaseDatabase.getInstance().reference


            val delQuery = ref.child(AppUser.login).orderByChild("name").equalTo(curfilename)




            val names=FirebaseDatabase.getInstance().getReference("filenamecontent")

            delQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (deletedsnap in dataSnapshot.children) {
                        deletedsnap.ref.removeValue()

                    }
                    val t= txt.text.toString()
                    ref.child(AppUser.login).push().setValue(onFile(curfilename, AppUser.login,t))

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })

            val nameQuery = names.orderByChild(AppUser.login).equalTo(curfilename)

            nameQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (deletedsnap in dataSnapshot.children) {
                        deletedsnap.ref.removeValue()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })


            names.child(AppUser.login).push().setValue(curfilename)




            Toast.makeText(this,"Документ сохранен",Toast.LENGTH_SHORT).show()

        }
    }

    override fun onBackPressed() {}

}



