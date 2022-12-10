package com.example.tp1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var fullnameView :EditText
    lateinit var addressView :EditText
    lateinit var pizzaSize:Array<String>
    lateinit var ingredients:Array<String>
    lateinit var spinner1 :Spinner
    lateinit var spinner2:Spinner



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         fullnameView= findViewById<EditText>(R.id.editTextFullName)
         addressView = findViewById<EditText>(R.id.editTextAdress)
         pizzaSize = resources.getStringArray(R.array.pizza_size)
         ingredients = resources.getStringArray(R.array.ingredients)
         spinner1 = findViewById<Spinner>(R.id.spinner)
         spinner2 = findViewById<Spinner>(R.id.spinner3)


        var adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, pizzaSize
        )
        spinner1.adapter = adapter
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, ingredients
        )
        spinner2.adapter = adapter
    }

    fun login(view: View) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("restaurant@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "make an order")
        val fullname=fullnameView.text
        val address=addressView.text
        var spinner1 = findViewById<Spinner>(R.id.spinner)
        var spinner2 = findViewById<Spinner>(R.id.spinner3)
        val size=spinner1.selectedItem.toString()
        val ingredient=spinner2.selectedItem.toString()
        var emailContent:String="fullname: $fullname \n Address: $address \n Size:  $size \n Ingredient: $ingredient"


        intent.putExtra(Intent.EXTRA_TEXT, emailContent)
        startActivity(Intent.createChooser(intent, "Email via..."))
    }
}