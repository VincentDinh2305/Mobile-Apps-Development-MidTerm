package com.example.dinhhoangvietphuong_comp304sec01_test01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import android.content.Intent
import android.widget.Toast
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.CheckBox
import com.example.dinhhoangvietphuong_comp304sec003_test1.R

class ProgramDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.program_details)


        val durations = arrayOf("2 semesters", "4 semesters", "6 semesters", "8 semesters")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, durations)
        val spinner = findViewById<Spinner>(R.id.spinnerDuration)
        spinner.adapter = adapter

        findViewById<Button>(R.id.btnNext).setOnClickListener {
            if (validateInputs()) {
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra("PROGRAM_NAME", findViewById<EditText>(R.id.etProgramName).text.toString())
                intent.putExtra("PROGRAM_SPONSORS", getSelectedSponsors())
                intent.putExtra("TUITION_PER_SEMESTER", findViewById<EditText>(R.id.etProgramCost).text.toString().toDouble())

                val numOfSemesters = when (spinner.selectedItem.toString()) {
                    "2 semesters" -> 2
                    "4 semesters" -> 4
                    "6 semesters" -> 6
                    "8 semesters" -> 8
                    else -> 1
                }

                intent.putExtra("NUM_OF_SEMESTERS", numOfSemesters)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please check your inputs", Toast.LENGTH_SHORT).show()
            }
        }


        findViewById<Button>(R.id.btnNext).setOnClickListener {
            if (validateInputs()) {
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra("PROJECT_NAME", findViewById<EditText>(R.id.etProgramName).text.toString())
                intent.putExtra("PROJECT_SPONSORS", getSelectedSponsors())
                intent.putExtra("PROJECT_COST", findViewById<EditText>(R.id.etProgramCost).text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please check your inputs", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val etProgramID = findViewById<EditText>(R.id.etProgramID).text.toString()
        val etProgramName = findViewById<EditText>(R.id.etProgramName).text.toString()
        val etProgramCost = findViewById<EditText>(R.id.etProgramCost).text.toString()
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupType)
        val course01 = findViewById<CheckBox>(R.id.checkCourse01).isChecked
        val course02 = findViewById<CheckBox>(R.id.checkCourse02).isChecked
        val course03 = findViewById<CheckBox>(R.id.checkCourse03).isChecked
        val course04 = findViewById<CheckBox>(R.id.checkCourse04).isChecked

        if (etProgramID.isEmpty() || etProgramID.length != 4) {
            Toast.makeText(this, "Program ID must be 4 digits long.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (etProgramName.isEmpty()) {
            Toast.makeText(this, "Program Name cannot be empty.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (radioGroup.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select a program type.", Toast.LENGTH_SHORT).show()
            return false
        }

        val selectedCourses = listOf(course01, course02, course03, course04).count { it }
        if (selectedCourses < 2) {
            Toast.makeText(this, "At least two courses must be selected.", Toast.LENGTH_SHORT).show()
            return false
        }


        if (etProgramCost.isEmpty()) {
            Toast.makeText(this, "Program Tuition Fee cannot be empty.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }


    private fun getSelectedSponsors(): String {
        val courses = mutableListOf<String>()
        if (findViewById<CheckBox>(R.id.checkCourse01).isChecked) courses.add("Course01")
        if (findViewById<CheckBox>(R.id.checkCourse02).isChecked) courses.add("Course02")
        if (findViewById<CheckBox>(R.id.checkCourse03).isChecked) courses.add("Course03")
        if (findViewById<CheckBox>(R.id.checkCourse04).isChecked) courses.add("Course04")
        return courses.joinToString(", ")
    }


}
