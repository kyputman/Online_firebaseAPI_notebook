package com.example.home_pc.samsung_proj

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.ColorSpace
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Login : AppCompatActivity() {

    lateinit var ProfileReference:DatabaseReference
    lateinit var loginlogin:String
    lateinit var passwordlogin:String
    lateinit var RegB:Button
    lateinit var LoginButton:Button
    override fun onBackPressed() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var profiles=mutableListOf<User>()

        RegB=findViewById<Button>(R.id.toRegB)
        LoginButton=findViewById<Button>(R.id.buttonlog)

        RegB.setOnClickListener {
            val toReg=Intent(this,Register::class.java)
            startActivityForResult(toReg,10)

        }

        val elog=findViewById<EditText>(R.id.textViewLog)
        val epas=findViewById<EditText>(R.id.textViewPass)

        LoginButton.setOnClickListener{
            loginlogin= elog.text.toString()
            passwordlogin=epas.text.toString()
            val ref=FirebaseDatabase.getInstance().getReference("Profile")
            val profilelistener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val auth=FirebaseAuth.getInstance()
                    ProfileReference = FirebaseDatabase.getInstance().getReference("Users")
                    dataSnapshot.children.forEach{
                        element-> if (element.getValue(User::class.java).login==loginlogin){
                        val returnLog = Intent()
                        returnLog.putExtra("login", loginlogin)
                        returnLog.putExtra("password", element.getValue(User::class.java).password)
                        setResult(1, returnLog)
                        finish()
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                }
            }
            ref.addValueEventListener(profilelistener)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == 10) {
            Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Зарегистрироваться не получилось", Toast.LENGTH_SHORT).show()
        }

    }



        fun findUser(a:MutableList<User>,w:String):User{
            a.forEach{
                element->if(element.login==w)return element
                Log.d("","finduser")
            }
            return a[0]
        }
        fun findUserIndex(a:MutableList<User>,w:User):Int {
            var g = 0;
            for (g in 1..a.size) {
                if(a[g].login==w.login)return g
            }
            return 0
        }

    }


