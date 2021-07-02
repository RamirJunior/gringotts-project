package alura.com.gringotts.presentation

import alura.com.gringotts.data.repositories.HomeRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val HomeRepository: HomeRepository) : ViewModel() {

    fun getHomeData() {
        viewModelScope.launch {
            HomeRepository.homeData()
        }
    }
}