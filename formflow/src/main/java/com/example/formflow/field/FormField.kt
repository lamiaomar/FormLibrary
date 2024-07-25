package com.example.formflow.field

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class FormField(
    val fields: ArrayList<Field>? = arrayListOf()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Field.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(fields)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<FormField> {
        override fun createFromParcel(parcel: Parcel): FormField = FormField(parcel)
        override fun newArray(size: Int): Array<FormField?> = arrayOfNulls(size)
    }
}
