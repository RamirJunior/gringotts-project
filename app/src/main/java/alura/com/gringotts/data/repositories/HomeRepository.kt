package alura.com.gringotts.data.repositories

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.HomeResponse
import alura.com.gringotts.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val sessionManager: SessionManager, private val api: ApiInterface) {

    suspend fun homeData(): HomeResponse {
        return withContext(Dispatchers.IO) {
            val token = sessionManager.getTokens()!!.tokenAuthentication
            val response = api.home(token)
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

}
