package alura.com.gringotts.data.repositories.pix_transference

import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.pix_transference.PixConfirmResponse
import alura.com.gringotts.data.models.pix_transference.PixValidation
import alura.com.gringotts.data.models.pix_transference.PixValidationResponse
import alura.com.gringotts.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PixRepository(private val sessionManager: SessionManager, private val api: ApiInterface) {

    suspend fun pixValidationData(pix: PixValidation): PixValidationResponse {
        return withContext(Dispatchers.IO) {
            val token = sessionManager.getTokens()!!.tokenAuthentication
            val response = api.pixValidation(pix, token)
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível acessar a api")
            }
        }
    }

    suspend fun pixConfirmData(pixToken: String): PixConfirmResponse {
        return withContext(Dispatchers.IO) {
            val token = sessionManager.getTokens()!!.tokenAuthentication
            val response = api.pixConfirm(token, pixToken)
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível acessar a api")
            }
        }
    }

    suspend fun balanceData(): Double {
        return withContext(Dispatchers.IO) {
            val token = sessionManager.getTokens()!!.tokenAuthentication
            val response = api.getHomeBalanceForPix(token)
            if (response.isSuccessful) {
                return@withContext response.body()!!.balance.currentValue
            } else {
                throw Exception("Não foi possível acessar o saldo")
            }
        }
    }

    fun saveBalancePixStateVisibility(isVisible: Boolean) =
        sessionManager.saveBalancePixStateVisibility(isVisible)

    fun getBalancePixStateVisibility(): Boolean = sessionManager.getBalancePixStateVisibility()

}
