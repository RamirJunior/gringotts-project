import alura.com.gringotts.data.AccountStatementDatabase
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.home.Transaction
import alura.com.gringotts.data.networkBoundResource
import alura.com.gringotts.data.session.SessionManager
import androidx.room.withTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class AccountStatementRepository(
    private val sessionManager: SessionManager,
    private val apiInterface: ApiInterface,
    private val db: AccountStatementDatabase
) {

    private val restaurantDao = db.AccountStatementDAO()

    suspend fun getAccountStatement(initialDate: String, finalDate: String): List<Transaction> {
        return withContext(Dispatchers.IO) {
            val token = sessionManager.getTokens()!!.tokenAuthentication
            val response = apiInterface.transactions(
                initialDate,
                finalDate,
                token
            )
            networkBoundResource(
                query = {
                    restaurantDao.getAllTransactions()
                },
                fetch = {
                    response.body()
                },
                saveFetchResult = { transaction ->
                    db.withTransaction {
                        restaurantDao.deleteAllTransactions()
                        if (transaction != null) {
                            restaurantDao.insertTransactions(transaction)
                        }
                    }
                }
            )
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Erro desconhecido")
            }
        }
    }

}
