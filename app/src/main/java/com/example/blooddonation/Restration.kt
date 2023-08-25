package com.example.blooddonation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.blooddonation.UserData.Methods
import com.google.firebase.auth.FirebaseAuth

class Restration : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restration)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        val resister : Button = findViewById(R.id.resisterBtn)
        val bloodGroup = resources.getStringArray(R.array.bloodGroup)

        val spinner : Spinner = findViewById(R.id.BloodGroup)
        val name : EditText = findViewById(R.id.fullName)
        val phone : EditText = findViewById(R.id.Contact)
        val aadhar : EditText = findViewById(R.id.aadhar_no)


        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodGroup)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                val data = spinner.toString()
                Toast.makeText(this@Restration, "text selected $data", Toast.LENGTH_SHORT ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action

            }
        }


        resister.setOnClickListener {
//            if(name.text.isNotEmpty() && phone.text.isNotEmpty() && aadhar.text.isNotEmpty()){
                Toast.makeText(this, "step 1", Toast.LENGTH_SHORT).show()
                auth.createUserWithEmailAndPassword(name.text.toString(),aadhar.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        Methods.setData(name.toString(), phone.toString(), aadhar.toString())
                        Toast.makeText(this, "successfull", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainHome::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
//                }
            }
            Toast.makeText(this, "directOut", Toast.LENGTH_SHORT).show()

        }
    }
}