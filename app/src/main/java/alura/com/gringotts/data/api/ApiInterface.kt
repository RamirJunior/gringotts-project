package alura.com.gringotts.data.api

import alura.com.gringotts.data.interceptors.HeaderInterceptor
import alura.com.gringotts.data.models.home.HomeResponse
import alura.com.gringotts.data.models.home.Transaction
import alura.com.gringotts.data.models.initial.LoginPayload
import alura.com.gringotts.data.models.initial.LoginResponse
import alura.com.gringotts.data.models.pix_transference.PixConfirmResponse
import alura.com.gringotts.data.models.pix_transference.PixValidation
import alura.com.gringotts.data.models.pix_transference.PixValidationResponse
import alura.com.gringotts.data.session.SessionManager
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor


interface ApiInterface {

    @POST("login")
    suspend fun userLogin(
        @Body login: LoginPayload
    ): Response<LoginResponse>

    @GET("extract")
    suspend fun transactions(
        @Query("start") initialDate: String,
        @Query("end") finalDate: String,
    ): Response<List<Transaction>>

    @GET("home")
    suspend fun home(): Response<HomeResponse>

    @POST("pix/validation")
    suspend fun pixValidation(
        @Body pix: PixValidation,
    ): Response<PixValidationResponse>

    @POST("pix/confirm")
    suspend fun pixConfirm(
        @Header("pix_token") pixToken: String
    ): Response<PixConfirmResponse>

    companion object {
        private const val BASE_URL =
            "https://us-central1-programa-de-bolsas---puc-2021.cloudfunctions.net/api/"

        fun create(sessionManager: SessionManager): ApiInterface {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient().newBuilder()
                .addInterceptor(HeaderInterceptor(sessionManager))
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}
