package alura.com.gringotts.data.Repositories

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.exceptions.NotFoundEmailException
import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.LoginResponse
import alura.com.gringotts.data.model.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository(private val sessionManager: SessionManager) {

    suspend fun userLogin(loginPayload: LoginPayload, rememberSwitch: Boolean){
        val response : Response<LoginResponse>
        withContext(Dispatchers.IO) {
            response = ApiInterface.create().userLogin(loginPayload)
        }
        if (response.isSuccessful) {
            loginSuccessHandler(response.body()!!, rememberSwitch, loginPayload)
        } else {
            loginFailedHandler(response.code())
        }
    }

    private fun loginFailedHandler(code: Int) {
        when (code) {
            NOT_FOUND_EMAIL -> {
                throw NotFoundEmailException("e-mail não encontrado")
            }
            INCOMPATIBLE_EMAIL_PASSWORD -> {
                throw NotFoundEmailException("e-mail não encontrado")
            }
            INCORRECT_PASSWORD -> {
                throw Exception("Senha incorreta")
            }
        }
    }

    private fun loginSuccessHandler(
        responseBody: LoginResponse, rememberSwitch: Boolean, loginPayload: LoginPayload
    ) {
        sessionManager.saveTokens(
            Token(responseBody.tokenAuthentication, responseBody.refreshToken)
        )
        if (rememberSwitch) {
            sessionManager.saveUserData(loginPayload)
        } else {
            sessionManager.deleteUserData()
        }
    }

    fun getUser(): LoginPayload? {
        return sessionManager.getUserData()
    }

    fun getTokens(): Token? {
        return sessionManager.getTokens()
    }

    companion object {
        private const val NOT_FOUND_EMAIL = 422
        private const val INCORRECT_PASSWORD = 401
        private const val INCOMPATIBLE_EMAIL_PASSWORD = 404
    }
}