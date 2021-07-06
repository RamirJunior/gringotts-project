package alura.com.gringotts.data

import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.Token
import alura.com.gringotts.data.model.User
import android.content.SharedPreferences
import com.google.gson.Gson

class SessionManagerImpl(private val sharedPreferences: SharedPreferences) : SessionManager {

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
        val userString = Gson().toJson(user)
        sharedPreferencesEditor.putString(USER_KEY, userString).commit()
    }

    override fun saveTokens(token: Token) {
        val tokensString = Gson().toJson(token)
        sharedPreferencesEditor.putString(TOKENS_KEY, tokensString).commit()
    }

    override fun deleteUserData() {
        sharedPreferencesEditor.remove(USER_KEY).commit()
    }

    override fun setOnboardingFinished() {
        sharedPreferencesEditor.putBoolean(FINISHED_KEY, true)
        sharedPreferencesEditor.apply()
    }

    override fun getOnboardingFinished(): Boolean {
        return sharedPreferences.getBoolean(FINISHED_KEY, false)
    }

    override fun saveUser(user: User) {
        val user = Gson().toJson(user)
        sharedPreferencesEditor.putString(USER, user).commit()
    }

    override fun getUser(): User? {
        val user = sharedPreferences.getString(USER, "")
        if (user.equals("")) return null
        return Gson().fromJson(user, User::class.java)
    }

    companion object {
        private const val USER_KEY = "user"
        private const val TOKENS_KEY = "tokens"
        private const val FINISHED_KEY = "finished"
        private const val HOME_RESPONSE = "homeBalance"
        private const val USER = "user"
    }

}

