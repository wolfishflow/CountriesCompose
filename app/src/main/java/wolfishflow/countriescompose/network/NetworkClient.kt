package wolfishflow.countriescompose.network

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkClient {

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(LoggingInterceptor.Builder().setLevel(Level.BODY).build())
            .build()
        //TODO add timeout
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = "https://restcountries.eu/rest/v2/"
    }
}
