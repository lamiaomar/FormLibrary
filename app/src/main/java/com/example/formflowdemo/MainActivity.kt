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
    }
}
