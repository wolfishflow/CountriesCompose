package wolfishflow.countriescompose.api

import retrofit2.http.GET
import wolfishflow.countriescompose.api.models.Country

interface CountriesService {

    @GET("name/canada")
    suspend fun getCanada(): List<Country>

}