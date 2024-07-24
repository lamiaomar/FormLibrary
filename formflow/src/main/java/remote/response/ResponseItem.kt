package remote.response

import com.squareup.moshi.Json

data class ResponseItem(
    @Json(name="Success")
    val Success: String = ""
)