package alura.com.gringotts.data.repositories

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class AccountStatementRepository(
    private val sessionManager: SessionManager,
    private val apiInterface: ApiInterface
) {

    suspend fun getAccountStatement(initialDate: String, finalDate: String): List<Transaction> {
        val response: Response<List<Transaction>>
        withContext(Dispatchers.IO) {
            response = ApiInterface.create().transactions(
                initialDate,
                finalDate,
                sessionManager.getTokens()!!.tokenAuthentication
            )
        }
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Erro desconhecido")
        }
    }
}