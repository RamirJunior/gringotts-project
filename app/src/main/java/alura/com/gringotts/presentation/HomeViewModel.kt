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

    private val _benefits = MutableLiveData<Boolean>()
    val benefits: LiveData<Boolean> = _benefits

    private val _balance = MutableLiveData<Boolean>()
    val balance: LiveData<Boolean> = _balance


    init {
        _loading.postValue(true)

        viewModelScope.launch {
            HomeRepository.homeData()
        }

        if (getBenefits() != null) {
            _benefits.postValue(true)
        }

        if (getBalance() != null) {
            _balance.postValue(true)
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
