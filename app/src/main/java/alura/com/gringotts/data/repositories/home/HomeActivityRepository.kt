package alura.com.gringotts.data.repositories.home

import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.home.SendFcmTokenPayload

class HomeActivityRepository(
    private val endPoint: ApiInterface
) {

    suspend fun sendToken(tokenFcm: String) {
        endPoint.getToken(SendFcmTokenPayload(tokenFcm))
    }
}
