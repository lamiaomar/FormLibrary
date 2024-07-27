package com.example.formflowdemo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.example.formflow.field.custome.CustomField

class CustomRatingField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs), CustomField {

    private val ratingBar: RatingBar
    private val label: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_custom_rating_field, this, true)
        ratingBar = view.findViewById(R.id.custom_field_rating)
        label = view.findViewById(R.id.custom_field_label)
    }

    override fun getValue(): String {
        return ratingBar.rating.toString()
    }

    override fun getView(): View {
        return this
    }
}