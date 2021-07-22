package alura.com.gringotts.data.repositories.pix_transference

import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.home.Balance
import alura.com.gringotts.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PixRepository(private val sessionManager: SessionManager, private val api: ApiInterface) {

    suspend fun pixData(): Balance {
        return withContext(Dispatchers.IO) {
            val token = sessionManager.getTokens()!!.tokenAuthentication
            val response = api.pix(token)
            if (response.isSuccessful) {
                return@withContext response.body()!!.balance
            } else {
                throw Exception("Não foi possível acessar o saldo")
            }
        }
    }

}