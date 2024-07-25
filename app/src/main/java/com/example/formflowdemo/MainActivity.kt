package com.example.formflowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.formflow.FormFlowFragment
import com.example.formflow.field.Field
import com.example.formflow.field.FieldType
import com.example.formflow.field.GoogleSheetURL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of usage
        GoogleSheetURL.saveBaseUrl(this, "https://script.google.com/macros/s/AKfycbw6SZN2nhRilS_hvNOlZLxcMlSzrTcJYSXGIRYcUOQNKc1DAoHsfHcb-IQTD1DMh2Ao/")

        val formFields = arrayListOf(
            Field(FieldType.EDITTEXT, "Question 22", "#808080", false),
            Field(FieldType.RADIO, "Question 1", "#808080", true, radioList = arrayListOf("lamya", "lamia", "lamyaa")),
            Field(FieldType.RADIO, "Question 1", "#808080", true,  radioList = arrayListOf("lamya", "lamia", "lamyaa")),
            Field(FieldType.DROPLIST, "Question 33", "#808080", true, dropList = arrayListOf("lamya", "lamia", "lamyaa"))
        )


        val fragment = FormFlowFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("formFields", formFields)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

    }
}
