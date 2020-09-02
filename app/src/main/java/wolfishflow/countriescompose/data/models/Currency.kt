package wolfishflow.countriescompose.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    val code: String?,
    val name: String?,
    val symbol: String?,
)
