package com.example.formflow.field

import android.os.Parcel
import android.os.Parcelable
import com.example.formflow.field.custome.CustomField

data class Field(
    val type: FieldType,
    val question: String,
    val color: String,
    val isMandatory: Boolean,
    var radioList: ArrayList<String> = arrayListOf(),
    var dropList: ArrayList<String> = arrayListOf(),
    var customField: CustomField? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        FieldType.values()[parcel.readInt()],
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeString(color)
        parcel.writeByte(if (isMandatory) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Field> {
        override fun createFromParcel(parcel: Parcel): Field {
            return Field(parcel)
        }

        override fun newArray(size: Int): Array<Field?> {
            return arrayOfNulls(size)
        }
    }
}