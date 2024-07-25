package com.example.formflow.field

import android.R
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView

object FormBuilder {
     fun addRadioButton(context: Context, container: LinearLayout, field: Field) {
        addTextView(context, container, field)
        val radioGroup = RadioGroup(context).apply {
            orientation = RadioGroup.HORIZONTAL
        }

        field.radioList.forEach { option ->
            val radioButton = RadioButton(context)
            radioButton.text = option
            radioButton.setTextColor(Color.parseColor(field.color))
            radioGroup.addView(radioButton)
        }
        container.addView(radioGroup)
        addSpacing(context, container, 20)
    }

     fun addDropList(context: Context, container: LinearLayout, field: Field) {
        addTextView(context, container, field)
        val spinner = Spinner(context).apply {
            val adapter =
                ArrayAdapter(context, R.layout.simple_spinner_item, field.dropList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.adapter = adapter
        }
        container.addView(spinner)
        addSpacing(context, container, 40)
    }


     fun addEditText(context: Context, container: LinearLayout, field: Field) {
        addTextView(context, container, field)
        val editText = EditText(context)
        editText.setTextColor(Color.parseColor(field.color))
        container.addView(editText)
        addSpacing(context, container, 20)
    }

    private fun addSpacing(context: Context, container: LinearLayout, space: Int) {
        val spaceView = View(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            space
        )
        spaceView.layoutParams = params
        container.addView(spaceView)
    }

    private fun addTextView(context: Context, container: LinearLayout, field: Field) {
        val textView = TextView(context)
        textView.text = field.question
        textView.setTextColor(Color.parseColor(field.color))
        container.addView(textView)
        addSpacing(context, container, 10)
    }
}