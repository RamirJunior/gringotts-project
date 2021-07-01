package alura.com.gringotts.data.api

import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.LoginResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("login")
    suspend fun userLogin(
        @Body login: LoginPayload
    ): Response<LoginResponse>

    companion object {
        private const val BASE_URL =
            "https://us-central1-programa-de-bolsas---puc-2021.cloudfunctions.net/api/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}
