package com.example.formflow.di

import remote.repo.FormRepository
import remote.service.GoogleSheetApi
import remote.service.GoogleSheetsService

object ServiceLocator {

    private var apiService: GoogleSheetsService? = null

    private fun provideApi(baseUrl: String): GoogleSheetsService {
        if (apiService == null) {
            apiService = GoogleSheetApi.createService(baseUrl)
        }
        return apiService!!
    }

    fun provideAppRepository(baseUrl: String): FormRepository {
        return FormRepository(provideApi(baseUrl))
    }
}