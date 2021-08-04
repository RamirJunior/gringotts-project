import alura.com.gringotts.data.AccountStatementDatabase
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.home.Transaction
import alura.com.gringotts.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext

class AccountStatementRepository(
    private val sessionManager: SessionManager,
    private val apiInterface: ApiInterface,
    db: AccountStatementDatabase
) {

    private val transactionDAO = db.accountStatementDAO()

    suspend fun getAccountStatement(
        initialDate: String,
        finalDate: String
    ): List<Transaction> {
        return withContext(Dispatchers.IO) {
            val response = apiInterface.transactions(
                initialDate,
                finalDate
            )

            if(response.code() == NEW_RESPONSE){
                transactionDAO.deleteAllTransactions()
                transactionDAO.insertTransactions(response.body()!!)
                return@withContext transactionDAO.getAllTransactions().single()
            }

            if(response.code() == SAME_RESPONSE) {
                return@withContext transactionDAO.getAllTransactions().single()
            } else {
                throw Exception("Erro desconhecido")
            }

        }
    }
    companion object {
        private const val NEW_RESPONSE = 200
        private const val SAME_RESPONSE = 304
    }

}


