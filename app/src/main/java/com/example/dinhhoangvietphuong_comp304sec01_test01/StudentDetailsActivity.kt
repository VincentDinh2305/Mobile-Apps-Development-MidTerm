package com.example.dinhhoangvietphuong_comp304sec01_test01

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.dinhhoangvietphuong_comp304sec003_test1.R


class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var programName: String
    private var totalTuitionFee: Int = 0
    private var numOfSemesters: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_details)

        // Retrieve data passed from ProgramDetailsActivity
        programName = intent.getStringExtra("PROJECT_NAME") ?: ""
        totalTuitionFee = intent.getIntExtra("TOTAL_TUITION_FEE", 0)
        numOfSemesters = intent.getIntExtra("NUM_OF_SEMESTERS", 0)

        // Display program details in TextViews
        findViewById<TextView>(R.id.tvProgramName).text = "Program Name: $programName"
        findViewById<TextView>(R.id.tvTotalTuitionFee).text = "Total Tuition Fee: $$totalTuitionFee"

        findViewById<Button>(R.id.btnPrev).setOnClickListener { finish() }
        findViewById<Button>(R.id.btnSubmit).setOnClickListener { onSubmitButtonClicked() }
    }

    private fun onSubmitButtonClicked() {
        val studentIDEditText = findViewById<EditText>(R.id.etStudentID)
        val studentNameEditText = findViewById<EditText>(R.id.etStudentName)
        val emailEditText = findViewById<EditText>(R.id.etEmail)

        val studentID = studentIDEditText.text.toString()
        val studentName = studentNameEditText.text.toString()
        val email = emailEditText.text.toString()

        // Validate inputs
        if (validateInputs(studentID, studentName, email)) {
            val toastMessage =
                "Program Name: $programName\nStudent Name: $studentName\nTotal Tuition Fee: $$totalTuitionFee\nNumber of Semesters: $numOfSemesters"
            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun validateInputs(studentID: String, studentName: String, email: String): Boolean {
        if (studentID.isEmpty()) {
            showToast("Student ID cannot be empty.")
            return false
        }

        if (studentName.isEmpty()) {
            showToast("Student Name cannot be empty.")
            return false
        }

        if (email.isEmpty()) {
            showToast("E-mail ID cannot be empty.")
            return false
        }
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}


