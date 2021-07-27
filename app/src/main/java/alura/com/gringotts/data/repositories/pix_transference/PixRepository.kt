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
                savePixToken(response.body()!!.pixToken)
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível acessar a api")
            }
        }
    }

    fun savePixToken(token: String) = sessionManager.savePixToken(token)

    fun getPixToken() = sessionManager.getPixToken()

    suspend fun pixConfirmData(): PixConfirmResponse {
        return withContext(Dispatchers.IO) {
            val token = sessionManager.getTokens()!!.tokenAuthentication
            val pixToken = getPixToken()!!
            val response = api.pixConfirm(token, pixToken)
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível acessar a api")
            }
        }
    }
}
