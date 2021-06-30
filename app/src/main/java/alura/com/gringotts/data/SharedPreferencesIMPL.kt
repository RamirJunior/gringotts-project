package alura.com.gringotts.data

import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.Tokens
import android.content.Context
import com.google.gson.Gson

class SharedPreferencesIMPL(context: Context) : SharedPreferencesProvider {

    private var gson = Gson()
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    private val sharedPreferencesEditor = sharedPreferences.edit()

    override fun getUserData(): LoginPayload? {
        val userString = sharedPreferences.getString(USER_KEY, "")
        if (userString.equals("")) return null
        return gson.fromJson(userString, LoginPayload::class.java)
    }

    override fun getTokens(): Tokens? {
        val tokensString = sharedPreferences.getString(TOKENS_KEY, "")
        if (tokensString.equals("")) return null
        return gson.fromJson(tokensString, Tokens::class.java)
    }

    override fun saveUserData(user: LoginPayload) {
        val userString = gson.toJson(user)
        sharedPreferencesEditor.putString(USER_KEY, userString)
    }

    override fun saveTokens(tokens: Tokens) {
        val tokensString = gson.toJson(tokens)
        sharedPreferencesEditor.putString(TOKENS_KEY, tokensString)
    }

    override fun deleteUserData() {
        sharedPreferencesEditor.remove(USER_KEY).commit()
    }

    companion object {
        private const val SHARED_PREFS = "sharedPrefs"
        private const val USER_KEY = "User"
        private const val TOKENS_KEY = "Tokens"
    }
}
