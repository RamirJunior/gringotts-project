package alura.com.gringotts.data.LoginRepository

import alura.com.gringotts.data.SharedPreferencesProvider
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.LoginPayload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(val sharedPreferencesProvider: SharedPreferencesProvider) {
    suspend fun userLogin(loginPayload: LoginPayload) = withContext(Dispatchers.IO) {
        ApiInterface.create().userLogin(loginPayload)
    }
    fun saveUserData
}