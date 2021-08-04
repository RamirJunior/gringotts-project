import alura.com.gringotts.data.AccountStatementDatabase
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.home.Transaction
import alura.com.gringotts.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
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
            val token = sessionManager.getTokens()!!.tokenAuthentication
            val response = apiInterface.transactions(
                initialDate,
                finalDate,
                token
            )

            if(response.code() == 200){ //Se n tiver dado
                transactionDAO.deleteAllTransactions()
                transactionDAO.insertTransactions(response.body()!!)
                return@withContext transactionDAO.getAllTransactions().single()
            }

            if(response.code() == 304) { //Se n tiver mudado nada e a api falar isso
                return@withContext transactionDAO.getAllTransactions().single()
            } else {
                throw Exception("Erro desconhecido")
            }

        }
    }

}

//            if(response.code() == 5000) { //Se a api tiver retornado que a resposta do servidor mudou e precisa atualizar o db
//                transactionDAO.deleteAllTransactions()
//                transactionDAO.insertTransactions(response.body()!!)
//
//            }

//            if (response.isSuccessful) {
//                return@withContext response.body()!!
//            }
