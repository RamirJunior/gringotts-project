package alura.com.gringotts.data.api

import alura.com.gringotts.data.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("userLogin")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>
    companion object{
        private val BASE_URL = "https://us-central1-programa-de-bolsas---puc-2021.cloudfunctions.net/pbpuc"


        fun create() : ApiInterface{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}