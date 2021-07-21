package alura.com.gringotts.presentation.pix

import alura.com.gringotts.data.session.SessionManager
import androidx.lifecycle.ViewModel

class OnboardingPixViewModel(private val sessionManager: SessionManager) : ViewModel() {

    fun onboardingPixFinished() {
        sessionManager.setOnboardingPixFinished()
    }

}
