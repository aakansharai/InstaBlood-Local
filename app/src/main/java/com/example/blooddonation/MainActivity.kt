package com.example.blooddonation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createBtn : Button = findViewById(R.id.createAccount)
        val signIn : Button = findViewById(R.id.signin)

        createBtn.setOnClickListener {
            val intent = Intent(this, Restration::class.java)
            startActivity(intent)
        }

        signIn.setOnClickListener {
            val intent = Intent(this, sign_in::class.java)
            startActivity(intent)
        }

    }
}