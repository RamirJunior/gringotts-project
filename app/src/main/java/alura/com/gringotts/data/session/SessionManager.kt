package alura.com.gringotts.data.session

import alura.com.gringotts.data.models.initial.LoginPayload
import alura.com.gringotts.data.models.initial.Token
import alura.com.gringotts.data.models.initial.User

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
    fun getOnboardingPixFinished(): Boolean
    fun setOnboardingPixFinished()
    fun saveBalancePixStateVisibility(isVisible: Boolean)
    fun getBalancePixStateVisibility(): Boolean
}
