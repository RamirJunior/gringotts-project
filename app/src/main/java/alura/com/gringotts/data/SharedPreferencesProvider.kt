package alura.com.gringotts.data

interface SharedPreferencesProvider {
    fun deleteUserData()
    fun getRemember(): Boolean
    fun getPassword(): String?
    fun getUsername(): String?
    fun setRemember(value: Boolean)
    fun saveUserData(username: String, password: String)
    fun saveResponse(tokenAuthentication: String, refreshToken: String)
}
