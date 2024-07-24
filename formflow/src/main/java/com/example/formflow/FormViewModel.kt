package com.example.formflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import remote.repo.FormRepository
import remote.request.FormItem

class FormViewModel(private val repository: FormRepository) : ViewModel() {
    private val _formResponse = MutableLiveData<String>()
    val formResponse: LiveData<String> get() = _formResponse

    fun sendFormData(formItem: FormItem) {
        _formResponse.value = "Sending data..."
        viewModelScope.launch {
            repository.sendFormData(formItem)
        }
    }
}
