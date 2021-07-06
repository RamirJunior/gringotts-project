package alura.com.gringotts.data.repositories

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.HomeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val sessionManager: SessionManager) {

    suspend fun homeData(): HomeResponse {
        val response = withContext(Dispatchers.IO) {
            val response = sessionManager.getTokens()
                ?.let { ApiInterface.create().home(it.tokenAuthentication) }!!
            response
        }
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Não foi possível realizar o acesso")
        }
    }
}

