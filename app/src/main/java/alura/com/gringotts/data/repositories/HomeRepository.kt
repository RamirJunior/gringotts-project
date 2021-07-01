package alura.com.gringotts.data.repositories

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.HomeResponse
import alura.com.gringotts.data.model.Token
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class HomeRepository(private val sessionManager: SessionManager) {
    suspend fun homeData(token: Token) {
        val response: Response<HomeResponse>
        withContext(Dispatchers.IO) {
            response = ApiInterface.create().home(token)
            Log.i("Home resposta", response.toString())
        }
    }
}