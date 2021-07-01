package alura.com.gringotts.presentation

import alura.com.gringotts.data.repositories.HomeRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private val _loading = MutableLiveData<Boolean>()
val loading: LiveData<Boolean> = _loading

class HomeViewModel : ViewModel() {
    private fun getHomeData() {
        viewModelScope.launch {
            HomeRepository.homeData()
        }
    }
}