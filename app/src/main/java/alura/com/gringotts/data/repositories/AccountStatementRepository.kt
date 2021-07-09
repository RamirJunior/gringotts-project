package alura.com.gringotts.data.repositories

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.TransactionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountStatementRepository(private val sessionManager: SessionManager) {

    suspend fun getAccountStatement(initialDate: String, finalDate: String): TransactionResponse {
        val response = withContext(Dispatchers.IO) {
            val response = sessionManager.getTokens()
                ?.let {
                    ApiInterface.create().transactions(
                        initialDate,
                        finalDate, it.tokenAuthentication
                    )
                }!!
            response
        }
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Não foi possível recuperar o extrato")
        }
    }
}