package alura.com.gringotts.presentation

import alura.com.gringotts.R
import alura.com.gringotts.data.model.Balance
import alura.com.gringotts.data.model.Benefit
import alura.com.gringotts.data.repositories.HomeRepository
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

    private lateinit var balanceValue: Balance

    private val _hideStatus = MutableLiveData<Boolean>()
    val hideStatus: LiveData<Boolean> = _hideStatus

    private val _visibilityId = MutableLiveData<Int>()
    val visibilityId: LiveData<Int> = _visibilityId

    private val _balance = MutableLiveData<String>()
    val balance: LiveData<String> = _balance
    private val _receivable = MutableLiveData<String>()
    val receivable: LiveData<String> = _receivable
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    init {
        _loading.postValue(true)

        viewModelScope.launch {
            val homeApiData = homeRepository.homeData()
            _benefits.postValue(homeApiData.benefits)
            balanceValue = homeApiData.balance
            _balance.postValue(balanceValue.currentValue.toString())
            _receivable.postValue(balanceValue.receivables.toString())
            setBalanceState(homeRepository.getHideStatus())
            _loading.postValue(false)
        }
        val user = homeRepository.getUser()
        _userName.postValue(user!!.firstName + " " +user.lastName)
    }

    fun hideBalanceButtonClicked() {
        val getCurrentBalanceVisibilityStatus = homeRepository.getHideStatus()
        val newCurrentBalanceVisibilityStatus = !getCurrentBalanceVisibilityStatus
        homeRepository.saveHideStatus(newCurrentBalanceVisibilityStatus)
        setBalanceState(newCurrentBalanceVisibilityStatus)
    }

    private fun setBalanceState(isBalanceVisible: Boolean) {
        if (isBalanceVisible) {
            _visibilityId.postValue(R.drawable.ic_baseline_visibility_off_24)
            showBalance()
        } else {
            _visibilityId.postValue(R.drawable.ic_baseline_visibility_24)
            hideBalance()
        }
    }

    private fun showBalance() {
        _balance.postValue(balanceValue.currentValue.toString())
        _receivable.postValue(balanceValue.receivables.toString())
    }

    private fun hideBalance() {
        _balance.postValue(HIDDENVALUE)
        _receivable.postValue(HIDDENVALUE)
    }

    companion object {
        private const val HIDDENVALUE = "* * * *"
    }
}
