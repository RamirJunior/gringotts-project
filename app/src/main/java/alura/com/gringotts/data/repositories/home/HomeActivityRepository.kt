package alura.com.gringotts.data.repositories.home

import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.home.TokenResponse

class HomeActivityRepository (
    private val EndPoint: ApiInterface) {
    suspend fun sendToken(tokenResponse: TokenResponse){
        EndPoint.getToken(tokenResponse)
    }
}
