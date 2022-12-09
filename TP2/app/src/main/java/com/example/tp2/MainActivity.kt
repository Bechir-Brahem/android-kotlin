package com.example.tp2

import StudentListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var spinnerView: Spinner
    lateinit var nRB: RadioButton
    lateinit var aRB: RadioButton
    lateinit var pRB: RadioButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerView = findViewById(R.id.spinner)
        nRB = findViewById(R.id.nRB)
        aRB = findViewById(R.id.aRB)
        pRB = findViewById(R.id.pRB)
        val groupRB = findViewById<RadioGroup>(R.id.radioGroup)


        var students = arrayListOf<Student>(
            Student("ahmed", "lotfi", true, false),
            Student("lahmer", "sondos", false, false),
            Student("lamis", "ben abdallah", false, true),
            Student("khmayes", "dembele", true, true),
            Student("umtiti", "ben ismali", true, true),
            Student("lamine", "lamia", false, false)
        )
        var studentListAdapter: StudentListAdapter = StudentListAdapter(students)
        var rvStudentsList = findViewById<View>(R.id.recyclerView) as RecyclerView
        rvStudentsList.adapter = studentListAdapter
        rvStudentsList.layoutManager = LinearLayoutManager(this)

        groupRB.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { groupd, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                if (radio.text == "No filter") {
                    studentListAdapter.noFilter()
                } else if (radio.text == "Absent") {
                    studentListAdapter.filterAbsent()
                } else if (radio.text == "Present") {
                    studentListAdapter.filterPresent()
                }
            }
        )


    }
}