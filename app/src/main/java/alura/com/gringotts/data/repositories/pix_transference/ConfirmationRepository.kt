package alura.com.gringotts.data.repositories.pix_transference

import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.pix_transference.PixConfirmation
import alura.com.gringotts.data.models.pix_transference.PixResponse
import alura.com.gringotts.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConfirmationRepository(private val sessionManager: SessionManager, private val api: ApiInterface) {

    suspend fun pixConfirmationData(pix: PixConfirmation): PixResponse {
        return withContext(Dispatchers.IO) {
            val token = sessionManager.getTokens()!!.tokenAuthentication
            val response = api.pixConfirm(pix, token)
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                throw Exception("Não foi possível acessar a api")
            }
        }
    }

}
