package alura.com.gringotts.presentation

import alura.com.gringotts.data.model.Balance
import alura.com.gringotts.data.model.Benefits
import alura.com.gringotts.data.repositories.HomeRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val HomeRepository: HomeRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getHomeData() {
        _loading.postValue(true)
        viewModelScope.launch {
            HomeRepository.homeData()
        }
        _loading.postValue(false)
    }

    fun getBenefits(): List<Benefits>? {
        return HomeRepository.getBenefits()
    }

    fun getBalance(): Balance? {
        return HomeRepository.getBalance()
    }

}