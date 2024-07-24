package remote.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import remote.request.FormItem
import remote.response.ResponseItem
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body

private const val BASE_URL =
    "https://script.google.com/macros/s/AKfycbylGbGCaHAB_MtCZHKIlkPtygRTbPDaHDzPSBwn0zrwv67Xeb2qNPjoVCEYK4MFB9Eu/"

interface GoogleSheetsService {
    @POST("exec")
    suspend fun submitForm(
        @Body requestBody: FormItem
    ): Response<ResponseItem>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object GoogleSheetApi {
    val retrofitService: GoogleSheetsService by lazy {
        retrofit.create(GoogleSheetsService::class.java)
    }
}