package com.example.formflow.di

import remote.repo.FormRepository
import remote.service.GoogleSheetApi
import remote.service.GoogleSheetsService

object ServiceLocator {
     private fun provideApi(): GoogleSheetsService = GoogleSheetApi.retrofitService

    fun provideAppRepository(): FormRepository = FormRepository(
        provideApi()
    )
}