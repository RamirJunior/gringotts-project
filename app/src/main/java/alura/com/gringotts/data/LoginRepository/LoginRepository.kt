package alura.com.gringotts.data.LoginRepository

import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.LoginModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository {
    suspend fun userLogin(loginModel: LoginModel) = withContext(Dispatchers.IO) {
        ApiInterface.create().userLogin(loginModel)
    }
}