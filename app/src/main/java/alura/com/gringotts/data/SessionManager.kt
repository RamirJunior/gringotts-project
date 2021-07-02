package alura.com.gringotts.data

import alura.com.gringotts.data.model.HomeResponse
import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.Token

interface SessionManager {
    fun deleteUserData()
    fun saveUserData(user: LoginPayload)
    fun saveTokens(token: Token)
    fun getTokens(): Token?
    fun getUserData(): LoginPayload?
    fun getOnboardingFinished(): Boolean
    fun setOnboardingFinished()
    fun getHomeResponse(): HomeResponse?
    fun saveHomeResponse(response: HomeResponse)
}
