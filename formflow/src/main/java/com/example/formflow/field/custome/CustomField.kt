package com.example.formflow.field.custome

import android.view.View

interface CustomField {
    fun getValue(): String
    fun getView(): View
}
