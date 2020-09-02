package wolfishflow.countriescompose.data

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class NetworkingModule {

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(LoggingInterceptor.Builder().setLevel(Level.BODY).build())
            .build()
        //TODO add timeout
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun getCountriesService() : CountriesService {
        return getRetrofit().create(CountriesService::class.java)
    }

    companion object {
        const val BASE_URL = "https://restcountries.eu/rest/v2/"
    }
}
