package alura.com.gringotts.presentation.home

import alura.com.gringotts.data.SessionManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeServicesViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private val _goToPixOnboarding = MutableLiveData<Boolean>()
    val goToPixOnboarding: LiveData<Boolean> = _goToPixOnboarding
    private val _goToPix = MutableLiveData<Boolean>()
    val goToPix: LiveData<Boolean> = _goToPix

    init {
        viewModelScope.launch {
            if (sessionManager.getOnboardingPixFinished()) {
                _goToPix.postValue(true)
            } else {
                _goToPixOnboarding.postValue(true)
            }
        }
    }

}
