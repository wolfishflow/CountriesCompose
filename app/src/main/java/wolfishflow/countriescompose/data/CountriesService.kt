package wolfishflow.countriescompose.data

import retrofit2.http.GET
import wolfishflow.countriescompose.data.models.Country

interface CountriesService {

    @GET("all")
    suspend fun getAllCountries(): List<Country>

    @GET("name/canada")
    suspend fun getCanada(): List<Country>
}