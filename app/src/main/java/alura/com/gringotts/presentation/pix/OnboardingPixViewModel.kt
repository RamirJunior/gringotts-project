package alura.com.gringotts.presentation.pix

import alura.com.gringotts.data.SessionManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OnboardingPixViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private val _goToPix = MutableLiveData<Boolean>()
    val goToPix: LiveData<Boolean> = _goToPix

    fun onboardingPixFinished() {
        sessionManager.setOnboardingPixFinished()
    }

    init {
        viewModelScope.launch {
            if (sessionManager.getOnboardingPixFinished()) {
                _goToPix.postValue(true)
            }
        }
    }

}
