package alura.com.gringotts.data

import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.Token
import android.content.Context
import com.google.gson.Gson

class SharedPreferencesIMPL(context: Context) : SharedPreferencesProvider {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    private val sharedPreferencesEditor = sharedPreferences.edit()

    override fun getUserData(): LoginPayload? {
        val userString = sharedPreferences.getString(USER_KEY, "")
        if (userString.equals("")) return null
        return Gson().fromJson(userString, LoginPayload::class.java)
    }

    override fun getTokens(): Token? {
        val tokensString = sharedPreferences.getString(TOKENS_KEY, "")
        if (tokensString.equals("")) return null
        return Gson().fromJson(tokensString, Token::class.java)
    }

    override fun saveUserData(user: LoginPayload) {
        val userString =  Gson().toJson(user)
        sharedPreferencesEditor.putString(USER_KEY, userString)
    }

    override fun saveTokens(token: Token) {
        val tokensString = Gson().toJson(token)
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
