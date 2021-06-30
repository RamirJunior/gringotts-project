package alura.com.gringotts.data

import android.content.Context

class InitialRepository (context : Context) {
    private val sharedPreferencesOnboarding = "sharedPreferencesInitial"
    private val finishedKey = "finished"

    private val sharedPreferences = context.getSharedPreferences(
        sharedPreferencesOnboarding, Context.MODE_PRIVATE)
    private val sharedPreferencesEditor = sharedPreferences.edit()

    fun setFinished(){
        sharedPreferencesEditor.putBoolean(finishedKey,true)
        sharedPreferencesEditor.apply()
    }

    fun getFinished() : Boolean {
        return sharedPreferences.getBoolean(finishedKey,false)
    }
}