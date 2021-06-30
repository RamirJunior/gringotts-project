package alura.com.gringotts.data

import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.Token

interface SessionManager {
    fun deleteUserData()
    fun saveUserData(user: LoginPayload)
    fun saveTokens(token: Token)
    fun getTokens(): Token?
    fun getUserData(): LoginPayload?
    fun getFinished(): Boolean
    fun setFinished()
}
