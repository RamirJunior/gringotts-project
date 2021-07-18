package alura.com.gringotts.presentation.home

import alura.com.gringotts.data.session.SessionManager
import androidx.lifecycle.ViewModel

class HomeServicesViewModel(private val sessionManager: SessionManager) : ViewModel() {

    fun onViewCreated(): Boolean {
        return !sessionManager.getOnboardingPixFinished()
    }
}
