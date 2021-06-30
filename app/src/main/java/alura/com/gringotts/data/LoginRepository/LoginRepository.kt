package alura.com.gringotts.data.LoginRepository

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(private val sessionManager: SessionManager) {

    suspend fun userLogin(loginPayload: LoginPayload) = withContext(Dispatchers.IO) {
        ApiInterface.create().userLogin(loginPayload)
    }

    fun saveUser(loginPayload: LoginPayload) {
        sessionManager.saveUserData(loginPayload)
    }

    fun saveTokens(token: Token) {
        sessionManager.saveTokens(token)
    }

    fun getUser(): LoginPayload? {
        return sessionManager.getUserData()
    }

    fun getTokens(): Token? {
        return sessionManager.getTokens()
    }

    fun deleteUserData() {
        sessionManager.deleteUserData()
    }
}