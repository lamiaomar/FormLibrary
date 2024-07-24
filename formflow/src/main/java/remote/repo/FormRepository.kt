package remote.repo

import android.util.Log
import remote.request.FormItem
import remote.response.ResponseItem
import remote.service.GoogleSheetsService

class FormRepository(private val apiService: GoogleSheetsService) {

    suspend fun sendFormData(formItem: FormItem): ResponseItem? {
        return try {
            val response = apiService.submitForm(formItem)

            if (response.isSuccessful) {
                Log.d("FormRepository", "Response successful: ${response.message()}")
                response.body()
            } else {
                Log.e("FormRepository", "Response failed: Fail")
                null
            }
        } catch (e: Exception) {
            Log.e("FormRepository", "Exception: ${e.message}")
            null
        }
    }
}
