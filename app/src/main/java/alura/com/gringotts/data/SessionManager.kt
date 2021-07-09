package alura.com.gringotts.data

import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.Token
import alura.com.gringotts.data.model.User

interface SessionManager {
    fun deleteUserData()
    fun saveUserData(user: LoginPayload)
    fun saveTokens(token: Token)
    fun getTokens(): Token?
    fun getUserData(): LoginPayload?
    fun getOnboardingFinished(): Boolean
    fun setOnboardingFinished()
    fun saveUser(user: User)
    fun getUser(): User?
    fun saveHideBalanceState(isVisible: Boolean)
    fun getHideBalanceState(): Boolean
}
