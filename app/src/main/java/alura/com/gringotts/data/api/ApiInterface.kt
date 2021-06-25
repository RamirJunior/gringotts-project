package alura.com.gringotts.data.api

import alura.com.gringotts.data.model.LoginModel
import alura.com.gringotts.data.model.LoginResponse
import alura.com.gringotts.data.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

  //  @FormUrlEncoded
    @POST
    fun userLogin(
      @Body login: LoginModel
    ): Call<LoginResponse>
    companion object{
        private const val BASE_URL = "https://us-central1-programa-de-bolsas---puc-2021.cloudfunctions.net/pbpuc/"


        fun create() : ApiInterface{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}