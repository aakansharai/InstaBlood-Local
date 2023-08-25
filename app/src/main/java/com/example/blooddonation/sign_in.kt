package com.example.blooddonation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class sign_in : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()

//        firebaseAuth = FirebaseAuth.getInstance()

        val phone: EditText = findViewById(R.id.phoneSignIn)
        val pass: EditText = findViewById(R.id.aadharSignIn)
        val signIn: Button = findViewById(R.id.SignInButton)

        signIn.setOnClickListener {
            if(phone.text.length<0 && pass.text.length<0) {
                Toast.makeText(this, "In Loop!", Toast.LENGTH_SHORT).show()
                firebaseAuth.signInWithEmailAndPassword(phone.text.toString(), pass.text.toString())
                    .addOnCompleteListener {
                        Toast.makeText(this, "Step 1!", Toast.LENGTH_SHORT).show()
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Logged In!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@sign_in, MainHome::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}