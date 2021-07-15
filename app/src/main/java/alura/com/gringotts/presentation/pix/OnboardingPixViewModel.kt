package alura.com.gringotts.presentation.pix

import alura.com.gringotts.data.SessionManager
import androidx.lifecycle.ViewModel

class OnboardingPixViewModel(private val sessionManager: SessionManager) : ViewModel() {

    fun onboardingPixFinished() {
        sessionManager.setOnboardingPixFinished()
    }

}
