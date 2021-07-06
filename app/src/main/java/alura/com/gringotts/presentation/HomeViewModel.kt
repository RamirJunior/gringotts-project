package alura.com.gringotts.presentation

import alura.com.gringotts.data.model.Balance
import alura.com.gringotts.data.model.Benefit
import alura.com.gringotts.data.model.User
import alura.com.gringotts.data.repositories.HomeRepository
import android.util.Log
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

    private var hideBalance = false
    private val _balance = MutableLiveData<String>()
    val balance: LiveData<String> = _balance
    private val _receivable = MutableLiveData<String>()
    val receivable: LiveData<String> = _receivable
    private val _userFirstName = MutableLiveData<String>()
    val userFirstName: LiveData<String> = _userFirstName
    private val _userLastName = MutableLiveData<String>()
    val userLastName: LiveData<String> = _userLastName

    init {
        _loading.postValue(true)

        viewModelScope.launch {
            val homeApiData = homeRepository.homeData()
            _benefits.postValue(homeApiData.benefits)
            balanceValue = homeApiData.balance
            _balance.postValue(balanceValue.currentValue.toString())
            _receivable.postValue(balanceValue.receivables.toString())
            _loading.postValue(false)
        }
        val user = homeRepository.getUser()
        _userFirstName.postValue(user!!.firstName)
        _userLastName.postValue(user.lastName)
    }

    fun hideBalanceButtonClicked() {
        hideBalance = if (!hideBalance) {
            _balance.postValue(HIDDENVALUE)
            _receivable.postValue(HIDDENVALUE)
            true
        } else {
            _balance.postValue(balanceValue.currentValue.toString())
            _receivable.postValue(balanceValue.receivables.toString())
            false
        }
    }

    companion object {
        private const val HIDDENVALUE = "* * * *"
    }
}
