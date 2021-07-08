package alura.com.gringotts.data.repositories

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.TransactionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountStatementRepository(private val sessionManager: SessionManager) {

    suspend fun extract(startDate: String, finishDate: String): TransactionResponse {
        val response = withContext(Dispatchers.IO) {
            val response = sessionManager.getTokens()
                ?.let {
                    ApiInterface.create().extract(startDate, finishDate, it.tokenAuthentication)
                }!!
            response
        }
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Não foi possível realizar o acesso")
        }
    }

    //fun saveFilterChoice(choice: Int) = sessionManager.saveFilterChoice(choice)

    //fun getFilterChoice(): Int = sessionManager.getFilterChoice()
}