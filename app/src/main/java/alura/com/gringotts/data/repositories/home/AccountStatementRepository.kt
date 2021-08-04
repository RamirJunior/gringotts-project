import alura.com.gringotts.data.AccountStatementDAO
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.home.Transaction
import alura.com.gringotts.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountStatementRepository(
    private val sessionManager: SessionManager,
    private val apiInterface: ApiInterface,
    private val transactionDAO: AccountStatementDAO
) {
    suspend fun getAccountStatement(
        initialDate: String,
        finalDate: String
    ): List<Transaction> {
        return withContext(Dispatchers.IO) {
            val token = sessionManager.getTokens()!!.tokenAuthentication
            val response = apiInterface.transactions(
                initialDate,
                finalDate,
                token
            )

            return@withContext when {
                response.code() == 200 -> { //Se n tiver dado
                    transactionDAO.deleteAllTransactions()
                    transactionDAO.insertTransactions(response.body()!!)
                    response.body()!!
                }
                response.code() == 304 -> { //Se n tiver mudado nada e a api falar isso
                    transactionDAO.getAllTransactions()
                }
                else -> {
                    throw Exception("Erro desconhecido")
                }
            }

        }
    }


}
