package com.example.tp2

import StudentListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var spinnerView: Spinner
    lateinit var nRB: RadioButton
    lateinit var aRB: RadioButton
    lateinit var pRB: RadioButton
    lateinit var txtEdit: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        val filter=studentListAdapter.filter
        txtEdit = findViewById(R.id.editTextTextPersonName)
        txtEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filter.filter("$p0")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


    }
}