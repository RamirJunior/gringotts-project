package alura.com.gringotts.data

interface SharedPreferencesProvider {
    fun deleteUserData()
    fun getRemeber(): Boolean
    fun getPassword(): String?
    fun getUsername(): String?
    fun setRemember(value: Boolean)
    fun saveUserData(username: String, password: String)
    fun saveResponse(token_authentication: String, refresh_token: String)
}
