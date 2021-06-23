package alura.com.gringotts.data

import android.content.Context
import android.util.Log

class SharedPreferencesProvider(context: Context) {
    private val sharedPref = "sharedPrefs"
    private val usernameKey = "username"
    private val passwordKey = "password"
    private val rememberKey = "remember"

    private val sharedPreferences = context.getSharedPreferences(sharedPref, Context.MODE_PRIVATE)
    val sharedPreferencesEditor = sharedPreferences.edit()
    fun getUsername(): String?{
        return sharedPreferences.getString(usernameKey, "")
    }
    fun getPassword(): String?{
        return sharedPreferences.getString(passwordKey, "")
    }
    fun getRemeber(): Boolean{
        return sharedPreferences.getBoolean(rememberKey, false)
    }
    fun setRemember(value: Boolean) {
        sharedPreferencesEditor.putBoolean(rememberKey, value).apply()
    }
    fun saveUserData(username: String, password: String){
        sharedPreferencesEditor.putString(usernameKey, username)
        sharedPreferencesEditor.putString(passwordKey, password).commit()
    }
    fun deleteUserData(){
        sharedPreferencesEditor.remove(usernameKey)
        sharedPreferencesEditor.remove(passwordKey).commit()
    }

}
