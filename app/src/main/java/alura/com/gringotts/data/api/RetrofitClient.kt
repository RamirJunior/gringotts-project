package alura.com.gringotts.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL =
        "https://us-central1-programa-de-bolsas---puc-2021.cloudfunctions.net/pbpuc"


    val instance: ApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiInterface::class.java)
    }
}
