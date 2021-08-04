package alura.com.gringotts.data.interceptors

import alura.com.gringotts.data.session.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            if (sessionManager.getTokens() != null) {
                request()
                    .newBuilder()
                    .addHeader("token", sessionManager.getTokens()!!.tokenAuthentication)
                    .build()
            } else {
                request().newBuilder().build()
            }
        )
    }

}
