package alura.com.gringotts.presentation

import alura.com.gringotts.data.model.Balance
import alura.com.gringotts.data.model.Benefit
import alura.com.gringotts.data.repositories.HomeRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val HomeRepository: HomeRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _benefits = MutableLiveData<List<Benefit>>()
    val benefits: LiveData<List<Benefit>> = _benefits

    private val _balance = MutableLiveData<Balance>()
    val balance: LiveData<Balance> = _balance

    init {
        _loading.postValue(true)

        viewModelScope.launch {
            val homeApiData = HomeRepository.homeData()
            _benefits.postValue(homeApiData.benefits)
            _balance.postValue(homeApiData.balance)
            _loading.postValue(false)
        }
    }
}
