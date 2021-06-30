package alura.com.gringotts.data

import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.Tokens

interface SharedPreferencesProvider {
    fun deleteUserData()
    fun saveUserData(user: LoginPayload)
    fun saveTokens(tokens: Tokens)
    fun getTokens(): Tokens?
    fun getUserData(): LoginPayload?
}
