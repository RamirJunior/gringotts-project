package alura.com.gringotts.presentation

import alura.com.gringotts.R
import alura.com.gringotts.data.InitialRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

class SplashViewModel(val initialRepository: InitialRepository) : ViewModel() {

    private val _goToOnboarding = MutableLiveData<Boolean>()
    val goToOnboarding: LiveData<Boolean> = _goToOnboarding

    private val _goToLogin = MutableLiveData<Boolean>()
    val goToLogin: LiveData<Boolean> = _goToLogin

    fun getFinished(): Boolean {
        return initialRepository.getFinished()
    }

    fun onboardingWasExecuted () {
        if (getFinished()) {
            goToLogin
        } else {
            goToOnboarding
        }
    }

}
