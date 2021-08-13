package alura.com.gringotts.data.session

import alura.com.gringotts.data.models.initial.LoginPayload
import alura.com.gringotts.data.models.initial.Token
import alura.com.gringotts.data.models.initial.User
import android.content.SharedPreferences
import com.google.gson.Gson

class SessionManagerImpl(private val sharedPreferences: SharedPreferences) : SessionManager {

    private val sharedPreferencesEditor = sharedPreferences.edit()

    override fun getUserData(): LoginPayload? {
        val userString = sharedPreferences.getString(LOGIN_PAYLOAD, "")
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
        sharedPreferencesEditor.putString(LOGIN_PAYLOAD, userString).commit()
    }

    override fun saveTokens(token: Token) {
        val tokensString = Gson().toJson(token)
        sharedPreferencesEditor.putString(TOKENS_KEY, tokensString).commit()
    }

    override fun deleteUserData() {
        sharedPreferencesEditor.remove(LOGIN_PAYLOAD).commit()
    }

    override fun setOnboardingFinished() {
        sharedPreferencesEditor.putBoolean(FINISHED_KEY, true)
        sharedPreferencesEditor.apply()
    }

    override fun getOnboardingFinished(): Boolean {
        return sharedPreferences.getBoolean(FINISHED_KEY, false)
    }

    override fun saveUser(user: User) {
        val userGson = Gson().toJson(user)
        sharedPreferencesEditor.putString(USER_KEY, userGson).apply()
    }

    override fun getUser(): User? {
        val user = sharedPreferences.getString(USER_KEY, "")
        if (user.equals("")) return null
        return Gson().fromJson(user, User::class.java)
    }

    override fun saveHideBalanceState(isVisible: Boolean) {
        sharedPreferencesEditor.putBoolean(HIDE_STATUS, isVisible)
        sharedPreferencesEditor.apply()
    }

    override fun getHideBalanceState(): Boolean {
        return sharedPreferences.getBoolean(HIDE_STATUS, false)
    }

    override fun setOnboardingPixFinished() {
        sharedPreferencesEditor.putBoolean(FINISHED_PIX_ONBOARDING_KEY, true)
        sharedPreferencesEditor.apply()
    }

    override fun getOnboardingPixFinished(): Boolean {
        return sharedPreferences.getBoolean(FINISHED_PIX_ONBOARDING_KEY, false)
    }

    override fun saveBalancePixStateVisibility(isVisible: Boolean) {
        sharedPreferencesEditor.putBoolean(BALANCE_PIX_STATE_VISIBILITY, isVisible)
        sharedPreferencesEditor.apply()
    }

    override fun getBalancePixStateVisibility(): Boolean {
        return sharedPreferences.getBoolean(BALANCE_PIX_STATE_VISIBILITY, false)
    }

    companion object {
        private const val LOGIN_PAYLOAD = "loginPayload"
        private const val TOKENS_KEY = "tokens"
        private const val FINISHED_KEY = "finished"
        private const val USER_KEY = "user"
        private const val HIDE_STATUS = "hideStatus"
        private const val FINISHED_PIX_ONBOARDING_KEY = "finishedPix"
        private const val BALANCE_PIX_STATE_VISIBILITY = "hideStatusPix"
    }

}
