import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.home.Transaction
import alura.com.gringotts.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class AccountStatementRepository(
    private val sessionManager: SessionManager,
    private val apiInterface: ApiInterface
) {

    suspend fun getAccountStatement(initialDate: String, finalDate: String): List<Transaction> {

         return withContext(Dispatchers.IO) {
             val token = sessionManager.getTokens()!!.tokenAuthentication
             val response = apiInterface.transactions(
                initialDate,
                finalDate,
                token
            )
             if (response.isSuccessful) {
                 return@withContext response.body()!!
             } else {
                 throw Exception("Erro desconhecido")
             }
        }
    }

}
