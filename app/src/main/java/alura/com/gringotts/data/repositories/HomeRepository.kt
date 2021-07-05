package alura.com.gringotts.data.repositories

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.Balance
import alura.com.gringotts.data.model.Benefits
import alura.com.gringotts.data.model.HomeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val sessionManager: SessionManager) {

    suspend fun homeData() {
        val response = withContext(Dispatchers.IO) {
            val response = sessionManager.getTokens()
                ?.let { ApiInterface.create().home(it.tokenAuthentication) }!!
            response
        }
        if (response.isSuccessful) {
            saveHomeResponse(response.body()!!)
        } else {
            throw Exception("Não foi possível realizar o acesso")
        }
    }

    fun getHomeResponse(): HomeResponse? {
        return sessionManager.getHomeResponse()
    }

    fun saveHomeResponse(homeResponse: HomeResponse) {
        return sessionManager.saveHomeResponse(homeResponse)
    }

    fun getBalance(): Balance {
        val response = getHomeResponse()
        return response!!.balance
    }

    fun getBenefits(): List<Benefits> {
        val response = getHomeResponse()
        return response!!.benefits
    }

}

