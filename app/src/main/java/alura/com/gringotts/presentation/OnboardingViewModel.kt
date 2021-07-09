package alura.com.gringotts.presentation

import alura.com.gringotts.data.SessionManager
import androidx.lifecycle.ViewModel

class OnboardingViewModel(private val sessionManager: SessionManager) : ViewModel() {

    fun onBoardingFinished() {
        sessionManager.setOnboardingFinished()
    }
}
