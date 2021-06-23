package alura.com.gringotts.data

import android.content.Context
import android.util.Log

class SharedPreferencesProvider(context: Context) {
    private val sharedPref = "sharedPrefs"
    private val usernameKey = "username"
    private val passwordKey = "password"
    private val rememberKey = "remember"

    private val sharedPreferences = context.getSharedPreferences(sharedPref, Context.MODE_PRIVATE)

    fun getUsername(): String?{
        Log.e("a", sharedPreferences.getString(usernameKey, "").toString())
        return sharedPreferences.getString(usernameKey, "")
    }
    fun getPassword(): String?{
        return sharedPreferences.getString(passwordKey, "")
    }
    fun getRemeber(): Boolean{
        return sharedPreferences.getBoolean(rememberKey, false)
    }
    fun setRemember(value: Boolean) {
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putBoolean(rememberKey, value).apply()
    }
    fun saveUserData(username: String, password: String){
        Log.e("zz", username)
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putString(usernameKey, username)
        sharedPreferencesEditor.putString(passwordKey, password).apply()
    }
    fun deleteUserData(){
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.remove(usernameKey)
        sharedPreferencesEditor.remove(passwordKey).apply()
    }

}