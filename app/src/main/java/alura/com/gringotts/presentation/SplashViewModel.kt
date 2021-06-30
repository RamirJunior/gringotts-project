package alura.com.gringotts.presentation

import alura.com.gringotts.data.SessionManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private val _goToOnboarding = MutableLiveData<Boolean>()
    val goToOnboarding: LiveData<Boolean> = _goToOnboarding

    private val _goToLogin = MutableLiveData<Boolean>()
    val goToLogin: LiveData<Boolean> = _goToLogin

    fun getOnboardingFinished(): Boolean {
        return sessionManager.getOnboardingFinished()
    }

    fun onboardingWasExecuted () {
        if (getOnboardingFinished()) {
            goToLogin
        } else {
            goToOnboarding
        }
    }

}
