package alura.com.gringotts.presentation.home

import alura.com.gringotts.data.repositories.home.HomeActivityRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeActivityViewModel(
    private val homeActivityRepository: HomeActivityRepository
) : ViewModel() {
    fun sendFcmToken(tokenFcm: String) {
        viewModelScope.launch {
            homeActivityRepository.sendToken(tokenFcm)
        }
    }
}
