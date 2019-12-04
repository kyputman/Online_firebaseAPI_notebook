package com.example.home_pc.samsung_proj

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import android.widget.ListView
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.database.FirebaseDatabase
import android.widget.TextView
import com.example.home_pc.samsung_proj.Main2Activity.a.curfilename
import com.example.home_pc.samsung_proj.Main2Activity.a.editval
import com.example.home_pc.samsung_proj.MainActivity.Companion.AppUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class onRoomFind : AppCompatActivity() {
    override fun onBackPressed() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_room_find)
        val roomreference = FirebaseDatabase.getInstance().reference
        val names=FirebaseDatabase.getInstance().getReference("filenamecontent").child(AppUser.login)
        val lv = findViewById<View>(R.id.list_room) as ListView
        val b=findViewById<Button>(R.id.backbutton)
        b.setOnClickListener { finish() }






        val myAdapter = object : FirebaseListAdapter<String>(this, String::class.java, R.layout.roomitem, names) {
            override fun populateView(view: View, s: String, i: Int) {
                    val text=view.findViewById(R.id.room_name)as TextView
                    text.text=s
                    val buttonjoin = view.findViewById(R.id.room_edit) as Button

                    buttonjoin.setOnClickListener {

                        curfilename = s

                        val ref = FirebaseDatabase.getInstance().getReference(AppUser.login)

                        val profilelistener = object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {

                                Log.d("list", "Login")
                                val auth = FirebaseAuth.getInstance()
                                Log.d("", "instance")
                                val Profile = FirebaseDatabase.getInstance().getReference(AppUser.login)
                                Log.d("", "reference")
                                dataSnapshot.children.forEach { element ->
                                    if (element.getValue(onFile::class.java).name == s) {
                                        Log.d("containing", "Login")
                                        editval = element.getValue(onFile::class.java).content

                                    }

                                }


                            }

                            override fun onCancelled(databaseError: DatabaseError) {

                            }
                        }

                        ref.addValueEventListener(profilelistener)
                        val tored=Intent(this@onRoomFind,Main2Activity::class.java)
                        startActivity(tored)
                    }


                }
            }
        lv.adapter = myAdapter
        }



     }


