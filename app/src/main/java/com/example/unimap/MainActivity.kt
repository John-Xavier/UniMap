package com.example.unimap

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLogin : Button = findViewById(R.id.loginBtn)
        btnLogin.setOnClickListener(){

//            val fireIntent : Intent = Intent(this,FirebaseUIActivity::class.java)
//            startActivity(fireIntent)
            val tabIntent : Intent = Intent(this,TabActivity::class.java)
            startActivity(tabIntent)
        }
    }
}