package com.example.formflow.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.formflow.FormViewModel
import remote.repo.FormRepository

class FormViewModelFactory(private val repository: FormRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FormViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}