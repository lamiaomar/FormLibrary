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
            Field(FieldType.EDITTEXT, "Question 1", "#808080", false),
            Field(FieldType.RADIO, "Question 2", "#808080", true, radioList = arrayListOf("Option 1", "Option 2", "Option 3")),
            Field(FieldType.RADIO, "Question 3", "#808080", true,  radioList = arrayListOf("Option 1", "Option 2", "Option 3")),
            Field(FieldType.DROPLIST, "Question 4", "#808080", true, dropList = arrayListOf("Option 1", "Option 2", "Option 3"))
        )


        val fragment = FormFlowFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("formFields", formFields)
                putString("submit_button_color", "#9f2b68")
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

    }
}
