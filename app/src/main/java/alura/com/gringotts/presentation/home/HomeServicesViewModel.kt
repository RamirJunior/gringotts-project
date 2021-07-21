package alura.com.gringotts.presentation.home

import alura.com.gringotts.data.session.SessionManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeServicesViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private val _goToOnboardingPix = MutableLiveData<Boolean>()
    val goToOnboardingPix: LiveData<Boolean> = _goToOnboardingPix

    fun onViewCreated() {
        val userPassedPixOnboarding = sessionManager.getOnboardingPixFinished()
        _goToOnboardingPix.postValue(!userPassedPixOnboarding)
    }

}
