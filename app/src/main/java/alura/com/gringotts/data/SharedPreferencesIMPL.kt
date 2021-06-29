package alura.com.gringotts.data

import android.content.Context

class SharedPreferencesIMPL(context: Context) : SharedPreferencesProvider {
    private val sharedPref = "sharedPrefs"
    private val usernameKey = "username"
    private val passwordKey = "password"
    private val rememberKey = "remember"
    private val token_authentication_key = "token_authentication"
    private val refresh_token_key = "refresh_token"

    private val sharedPreferences = context.getSharedPreferences(sharedPref, Context.MODE_PRIVATE)
    private val sharedPreferencesEditor = sharedPreferences.edit()
    override fun getUsername(): String? {
        return sharedPreferences.getString(usernameKey, "")
    }

    override fun getPassword(): String? {
        return sharedPreferences.getString(passwordKey, "")
    }

    override fun getRemember(): Boolean {
        return sharedPreferences.getBoolean(rememberKey, false)
    }

    override fun setRemember(value: Boolean) {
        sharedPreferencesEditor.putBoolean(rememberKey, value).commit()
    }

    override fun saveUserData(username: String, password: String) {
        sharedPreferencesEditor.putString(usernameKey, username)
        sharedPreferencesEditor.putString(passwordKey, password).commit()
    }

    override fun deleteUserData() {
        sharedPreferencesEditor.remove(usernameKey)
        sharedPreferencesEditor.remove(passwordKey).commit()
    }

    override fun saveResponse(tokenAuthentication: String, refreshToken: String) {
        sharedPreferencesEditor.putString(token_authentication_key, tokenAuthentication)
        sharedPreferencesEditor.putString(refresh_token_key, refreshToken).commit()
    }
}
