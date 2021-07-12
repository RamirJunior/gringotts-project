package alura.com.gringotts.data.api

import alura.com.gringotts.data.model.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

    @POST("login")
    suspend fun userLogin(
        @Body login: LoginPayload
    ): Response<LoginResponse>

    @GET("extract")
    suspend fun transactions(
        @Query("start") initialDate: String,
        @Query("end") finalDate: String,
        @Header("token") token: String
    ): Response<List<Transaction>>

    @GET("home")
    suspend fun home(
        @Header("token") token: String
    ): Response<HomeResponse>

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
