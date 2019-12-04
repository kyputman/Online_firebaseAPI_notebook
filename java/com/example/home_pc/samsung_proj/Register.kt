package com.example.home_pc.samsung_proj

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Register : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val newlogedit = findViewById<EditText>(R.id.textViewLogReg)
        newlogedit.setOnClickListener{if (newlogedit.text.toString()=="Новый Логин")newlogedit.text.clear()}
        val newpasedit = findViewById<EditText>(R.id.textViewPassReg)
        newpasedit.setOnClickListener{if (newpasedit.text.toString()=="Новый Пароль")newpasedit.text.clear()}
        val confirmreg = findViewById<Button>(R.id.buttonreg)
        confirmreg.setOnClickListener {


            val ProfileReference = FirebaseDatabase.getInstance().getReference("Profile")
            val l=newlogedit.text.toString()
            val p=newpasedit.text.toString()


            ProfileReference.push().setValue(User(l,p))



            val returnReg = Intent()

            returnReg.putExtra("loginReg", l)
            returnReg.putExtra("passwordReg", p)
            setResult(1,returnReg)



            finish()
        }


    }
}
