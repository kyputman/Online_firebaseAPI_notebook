package com.example.home_pc.samsung_proj

import android.app.ListActivity

import android.preference.PreferenceManager
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.view.View
import android.view.Window
import android.widget.*
import bolts.Task.call
import com.example.home_pc.samsung_proj.R.layout.dialog

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import org.w3c.dom.Text
import android.widget.Toast
import android.content.DialogInterface
import android.widget.EditText
import com.example.home_pc.samsung_proj.Main2Activity.a.curfilename


class MainActivity : Activity(){


    lateinit  var sharedPreferences:SharedPreferences
    lateinit  var editor:SharedPreferences.Editor
    lateinit  var profilereference: DatabaseReference
    lateinit  var databaseprofile: FirebaseDatabase
    lateinit var libref:DatabaseReference
    lateinit var tvt:TextView
    companion object {
        @JvmStatic
        internal lateinit  var AppUser:User
        @JvmStatic
        internal var isnew=false
    }
    override fun onBackPressed() {}


    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var t  =mutableListOf<onFile>()
        val newfile=findViewById<Button>(R.id.newfile)
        val openfile=findViewById<Button>(R.id.openfiles)

        val quit=findViewById(R.id.quit)as Button
        val toLog=Intent(this,Login::class.java)

        startActivityForResult(toLog,1)

        quit.setOnClickListener{
            finish()
        }

        openfile.setOnClickListener{
            isnew=false
            val tobrowse=Intent(this,onRoomFind::class.java)
            startActivity(tobrowse)

        }
        newfile.setOnClickListener{
            isnew=true
                val builder = AlertDialog.Builder(this);

            val alert = AlertDialog.Builder(this)

            alert.setTitle("создание файла")
            alert.setMessage("введите имя файла")

            val input = EditText(this)
            alert.setView(input)

            alert.setPositiveButton("создать") { dialog, whichButton ->
                val value = input.text.toString()
                curfilename=value
                val tocreate=Intent(this,Main2Activity::class.java)
                startActivity(tocreate)
            }

            alert.setNegativeButton("отмена") { dialog, whichButton ->

            }

            alert.show()

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == 1) {

            AppUser= User(data.getStringExtra("login"),data.getStringExtra("password"))
            Toast.makeText(this, "Вы вошли", Toast.LENGTH_SHORT).show()
            tvt=findViewById(R.id.textview)as TextView
            tvt.text=" Здравствуйте, "+ AppUser.login+"! "
            libref=FirebaseDatabase.getInstance().getReference(AppUser.login)

        } else {
            Toast.makeText(this, "Войти не получилось", Toast.LENGTH_SHORT).show()
            finish()
        }

    }



}


