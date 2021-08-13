package alura.com.gringotts.data.repositories.initial

import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.exceptions.NotFoundEmailException
import alura.com.gringotts.data.models.initial.LoginPayload
import alura.com.gringotts.data.models.initial.LoginResponse
import alura.com.gringotts.data.models.initial.Token
import alura.com.gringotts.data.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository(
    private val sessionManager: SessionManager,
    private val apiInterface: ApiInterface
) {

    suspend fun userLogin(loginPayload: LoginPayload, rememberSwitch: Boolean) {
        val response: Response<LoginResponse>
        withContext(Dispatchers.IO) {
            response = apiInterface.userLogin(loginPayload)
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
                throw NotFoundEmailException("e-mail ou senha invalida")
            }
        }
    }

    private fun loginSuccessHandler(
        responseBody: LoginResponse,
        rememberSwitch: Boolean,
        loginPayload: LoginPayload
    ) {
        sessionManager.saveUser(responseBody.user)
        sessionManager.saveTokens(
            Token(responseBody.tokenAuthentication)
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

    companion object {
        private const val NOT_FOUND_EMAIL = 422
        private const val INCOMPATIBLE_EMAIL_PASSWORD = 404
    }
}
