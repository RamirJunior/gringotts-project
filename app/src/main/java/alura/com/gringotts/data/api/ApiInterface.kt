package alura.com.gringotts.data.api

import alura.com.gringotts.data.models.home.HomeResponse
import alura.com.gringotts.data.models.home.Transaction
import alura.com.gringotts.data.models.initial.LoginPayload
import alura.com.gringotts.data.models.initial.LoginResponse
import alura.com.gringotts.data.models.pix_transference.PixConfirmation
import alura.com.gringotts.data.models.pix_transference.PixResponse
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

    @POST("pix/confirm")
    suspend fun pixConfirm(
        @Body pix: PixConfirmation,
        @Header("token") token: String
    ): Response<PixResponse>

//    @POST("pix/validation")
//    suspend fun pixValidation(
//        @Header("token") token: String
//    ): Response<PixResponse>


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
