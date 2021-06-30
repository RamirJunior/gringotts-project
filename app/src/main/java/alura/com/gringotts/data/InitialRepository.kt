package alura.com.gringotts.data

import android.content.Context

class InitialRepository (context : Context) {
    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_ONBOARDING, Context.MODE_PRIVATE)
    private val sharedPreferencesEditor = sharedPreferences.edit()

    fun setFinished(){
        sharedPreferencesEditor.putBoolean(FINISHED_KEY,true)
        sharedPreferencesEditor.apply()
    }

    fun getFinished() : Boolean {
        return sharedPreferences.getBoolean(FINISHED_KEY,false)
    }

    companion object{
        private const val SHARED_PREFS_ONBOARDING = "sharedPreferencesInitial"
        private const val FINISHED_KEY = "finished"
    }
}