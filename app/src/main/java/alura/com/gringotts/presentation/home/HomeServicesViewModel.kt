package alura.com.gringotts.presentation.home

import alura.com.gringotts.data.SessionManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeServicesViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private val _goToOnboardingPix = MutableLiveData<Boolean>()
    val goToOnboardingPix: LiveData<Boolean> = _goToOnboardingPix

    init {
        viewModelScope.launch {
            if (sessionManager.getOnboardingPixFinished()) {
                _goToOnboardingPix.postValue(false)
            } else {
                _goToOnboardingPix.postValue(true)
            }
        }
    }
}
