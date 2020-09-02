package wolfishflow.countriescompose.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val name: String,
    val alpha2Code: String,
    val alpha3Code: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val population: Int,
    val demonym: String,
    val currencies: List<Currency>,
    @Json(name = "flag")
    val flagImageUrl: String
)