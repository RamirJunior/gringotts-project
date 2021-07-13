package alura.com.gringotts.presentation.home

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.Balance
import alura.com.gringotts.data.models.home.Benefit
import alura.com.gringotts.data.repositories.home.HomeRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _benefits = MutableLiveData<List<Benefit>>()
    val benefits: LiveData<List<Benefit>> = _benefits
    private val _visibilityId = MutableLiveData<Int>()
    val visibilityId: LiveData<Int> = _visibilityId
    private val _balance = MutableLiveData<String>()
    val balance: LiveData<String> = _balance
    private val _receivable = MutableLiveData<String>()
    val receivable: LiveData<String> = _receivable
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName
    private val _apiError = MutableLiveData<String>()
    val apiError: LiveData<String> = _apiError

    private lateinit var balanceValue: Balance

    init {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val homeData = homeRepository.homeData()
                _benefits.postValue(homeData.benefits)
                balanceValue = homeData.balance
                _balance.postValue(balanceValue.currentValue.toString())
                _receivable.postValue(balanceValue.receivables.toString())
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
            showBalance()
        } else {
            hideBalance()
        }
    }

    private fun showBalance() {
        _visibilityId.postValue(R.drawable.ic_baseline_visibility_off_24)
        _balance.postValue(balanceValue.currentValue.toString())
        _receivable.postValue(balanceValue.receivables.toString())
    }

    private fun hideBalance() {
        _visibilityId.postValue(R.drawable.ic_baseline_visibility_24)
        _balance.postValue(HIDDENVALUE)
        _receivable.postValue(HIDDENVALUE)
    }

    companion object {
        private const val HIDDENVALUE = "* * * *"
    }
}
