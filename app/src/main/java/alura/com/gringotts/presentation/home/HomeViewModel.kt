package alura.com.gringotts.presentation.home

import alura.com.gringotts.data.models.home.Balance
import alura.com.gringotts.data.models.home.Benefit
import alura.com.gringotts.data.repositories.home.HomeRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.NumberFormat

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _benefits = MutableLiveData<List<Benefit>>()
    val benefits: LiveData<List<Benefit>> = _benefits
    private val _balance = MutableLiveData<String>()
    val balance: LiveData<String> = _balance
    private val _receivable = MutableLiveData<String>()
    val receivable: LiveData<String> = _receivable
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName
    private val _apiError = MutableLiveData<String>()
    val apiError: LiveData<String> = _apiError
    private val _hideBalanceAndReceivable = MutableLiveData<Boolean>()
    val hideBalanceAndReceivable : LiveData<Boolean> = _hideBalanceAndReceivable

    private lateinit var balanceValue: Balance

    init {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val homeData = homeRepository.homeData()
                _benefits.postValue(homeData.benefits)
                balanceValue = homeData.balance
                _balance.postValue(NumberFormat.getInstance().format(balanceValue.currentValue))
                _receivable.postValue(NumberFormat.getInstance().format(balanceValue.receivables.toString()))
                setBalanceState(homeRepository.getHideBalanceState())
                val user = homeRepository.getUser()
                _userName.postValue(user!!.firstName + " " + user.lastName)
                _loading.postValue(false)
            } catch (e: Exception) {
                _apiError.postValue("Sem acesso a internet")
            }
        }
    }

    fun hideBalanceButtonClicked() {
        val newCurrentBalanceVisibilityStatus = !homeRepository.getHideBalanceState()
        homeRepository.saveHideBalanceState(newCurrentBalanceVisibilityStatus)
        setBalanceState(newCurrentBalanceVisibilityStatus)
    }

    private fun setBalanceState(isBalanceVisible: Boolean) {
        if (isBalanceVisible) {
            _hideBalanceAndReceivable.postValue(false)
        } else {
            _hideBalanceAndReceivable.postValue(true)
        }
    }

}
