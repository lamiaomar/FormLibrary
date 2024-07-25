package com.example.formflow.field

import android.content.Context
import android.content.Context.MODE_PRIVATE

object GoogleSheetURL {
    fun saveBaseUrl(context: Context, baseUrl: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_KEY, MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_BASE_URL, baseUrl).apply()
    }

    fun getBaseUrl(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_KEY, MODE_PRIVATE)
        return sharedPreferences.getString(KEY_BASE_URL, null)
    }

    private const val PREF_KEY = "com.example.app.PREFERENCE_FILE_KEY"
    private const val KEY_BASE_URL = "base_url"
}