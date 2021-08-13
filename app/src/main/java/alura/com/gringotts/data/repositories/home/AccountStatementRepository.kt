import alura.com.gringotts.data.AccountStatementDatabase
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.home.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountStatementRepository(
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

            return@withContext when {
                response.code() == NEW_RESPONSE -> {
                    transactionDAO.deleteAllTransactions()
                    transactionDAO.insertTransactions(response.body()!!)
                    response.body()!!
                }
                response.code() == SAME_RESPONSE -> {
                    transactionDAO.getAllTransactions()
                }
                else -> {
                    throw Exception("Erro desconhecido")
                }
            }

        }

    }

    companion object {
        private const val NEW_RESPONSE = 200
        private const val SAME_RESPONSE = 304
    }

}

