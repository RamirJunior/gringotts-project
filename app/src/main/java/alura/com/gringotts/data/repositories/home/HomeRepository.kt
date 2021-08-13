package alura.com.gringotts.data.repositories.home

import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.home.HomeResponse
import alura.com.gringotts.data.models.home.SendFcmTokenPayload
import alura.com.gringotts.data.models.initial.User
import alura.com.gringotts.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val sessionManager: SessionManager, private val api: ApiInterface) {

    suspend fun homeData(): HomeResponse {
        return withContext(Dispatchers.IO) {
            val response = api.home()
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível realizar o acesso")
            }
        }
    }

    fun getUser(): User? = sessionManager.getUser()

    fun saveHideBalanceState(isVisible: Boolean) = sessionManager.saveHideBalanceState(isVisible)

    fun getHideBalanceState(): Boolean = sessionManager.getHideBalanceState()

    suspend fun sendToken(sendFcmTokenPayload: SendFcmTokenPayload) {
        api.getToken(sendFcmTokenPayload)
    }

}
