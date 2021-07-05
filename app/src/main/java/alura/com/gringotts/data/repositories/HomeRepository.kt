package alura.com.gringotts.data.repositories

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.Balance
import alura.com.gringotts.data.model.HomeResponse
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val sessionManager: SessionManager) {

    suspend fun homeData() {
        val response = withContext(Dispatchers.IO) {
            val response = sessionManager.getTokens()
                ?.let { ApiInterface.create().home(it.tokenAuthentication) }!!
            response.body()!!
        }
        saveHomeResponse(response)
    }

    fun getHomeResponse(): HomeResponse? {
        return sessionManager.getHomeResponse()
    }

    fun saveHomeResponse(homeResponse: HomeResponse) {
        return sessionManager.saveHomeResponse(homeResponse)
    }

    fun getBalance(): Balance {
        val response = getHomeResponse()
        val balance = response!!.balance
        return balance
    }

}
