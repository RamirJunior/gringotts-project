package alura.com.gringotts.presentation.initial

import alura.com.gringotts.data.session.SessionManager
import androidx.lifecycle.ViewModel

class OnboardingViewModel(private val sessionManager: SessionManager) : ViewModel() {

    fun onBoardingFinished() {
        sessionManager.setOnboardingFinished()
    }

}
