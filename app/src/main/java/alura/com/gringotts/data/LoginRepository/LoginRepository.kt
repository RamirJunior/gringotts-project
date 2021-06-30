package alura.com.gringotts.data.LoginRepository

import alura.com.gringotts.data.SharedPreferencesProvider
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.Tokens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(private val sharedPreferencesProvider: SharedPreferencesProvider) {

    suspend fun userLogin(loginPayload: LoginPayload) = withContext(Dispatchers.IO) {
        ApiInterface.create().userLogin(loginPayload)
    }

    fun saveUser(loginPayload: LoginPayload){
        sharedPreferencesProvider.saveUserData(loginPayload)
    }

    fun saveTokens(tokens: Tokens){
        sharedPreferencesProvider.saveTokens(tokens)
    }

    fun getUser(): LoginPayload?{
        return sharedPreferencesProvider.getUserData()
    }

    fun getTokens(): Tokens?{
        return sharedPreferencesProvider.getTokens()
    }
    fun deleteUserData(){
        sharedPreferencesProvider.deleteUserData()
    }
}