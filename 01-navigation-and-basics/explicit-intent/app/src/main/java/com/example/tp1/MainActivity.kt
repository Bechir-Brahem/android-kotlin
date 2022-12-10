package com.example.tp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var txtEmail: EditText
    lateinit var txtPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtEmail = findViewById(R.id.editTextTextEmailAddress)
        txtPassword = findViewById(R.id.editTextTextPassword)

    }

    fun login(view: View) {
        var email = txtEmail.text.toString()
        var password = txtPassword.text.toString()
        if (email == "gl4@insat.tn" && password == "insat2022") {
            val intent = Intent(view.context,WelcomeActivity::class.java)
            intent.putExtra("email",email)
            startActivity(intent)


        }else{
            Toast.makeText(applicationContext, "wrong password or email", Toast.LENGTH_SHORT).show()
        }

    }
}