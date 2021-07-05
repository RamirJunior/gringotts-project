package alura.com.gringotts.presentation

import alura.com.gringotts.data.model.Balance
import alura.com.gringotts.data.model.Benefits
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

    fun getBenefits(): List<Benefits> {
        return HomeRepository.getBenefits()
    }

    fun getBalance(): Balance? {
        return HomeRepository.getBalance()
    }

}