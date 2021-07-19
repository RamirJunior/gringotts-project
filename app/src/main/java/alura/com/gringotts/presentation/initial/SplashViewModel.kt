package alura.com.gringotts.presentation.initial

import alura.com.gringotts.data.session.SessionManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel(sessionManager: SessionManager) : ViewModel() {

    private val _goToOnboarding = MutableLiveData<Boolean>()
    val goToOnboarding: LiveData<Boolean> = _goToOnboarding

    private val _goToLogin = MutableLiveData<Boolean>()
    val goToLogin: LiveData<Boolean> = _goToLogin

    init {
        if (sessionManager.getOnboardingFinished()) {
            _goToLogin.postValue(true)
        } else {
            _goToOnboarding.postValue(true)
        }
    }

}
