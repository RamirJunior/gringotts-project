package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.repositories.pix_transference.PixActualAccountValueRepository
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PixValueViewModel(private val pixActualAccountValueRepository: PixActualAccountValueRepository) :
    ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _balance = MutableLiveData<String>()
    val balance: LiveData<String> = _balance
    private val _apiError = MutableLiveData<String>()
    val apiError: LiveData<String> = _apiError
    private val _invalidValueError = MutableLiveData<String?>()
    val invalidValueError: LiveData<String?> = _invalidValueError
    private val _goToConfirmationPixFragment = SingleLiveEvent<String>()
    val goToConfirmationPixFragment: LiveData<String> = _goToConfirmationPixFragment

    var pixValue: String = "0.0"

    private lateinit var balanceValue: String

    init {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                balanceValue = pixActualAccountValueRepository.balanceData().toString()
                _balance.postValue(balanceValue)
                setBalanceStatePix(pixActualAccountValueRepository.getHideBalanceStatePix())
            } catch (e: Exception) {
                _apiError.postValue("Sem acesso a internet")
            }
            _loading.postValue(false)
        }
    }

    fun onValueButtonClicked() {
        if (pixValue.toDouble() <= balance.value!!.toDouble() && pixValue.toDouble() > 0) {
            _goToConfirmationPixFragment.postValue(pixValue)
            _invalidValueError.postValue(null)
        } else if (pixValue.toDouble() > balance.value!!.toDouble()) {
            _invalidValueError.postValue("Valor da transferência maior que saldo")
        } else if (pixValue.toDouble() <= 0) {
            _invalidValueError.postValue("Transferência deve ser de pelo menos 0,01.")
        }
    }

    fun hideBalanceButtonClickedPix() {
        val newCurrentBalanceVisibilityStatus =
            !pixActualAccountValueRepository.getHideBalanceStatePix()
        pixActualAccountValueRepository.saveHideBalanceStatePix(newCurrentBalanceVisibilityStatus)
        setBalanceStatePix(newCurrentBalanceVisibilityStatus)
    }

    private fun setBalanceStatePix(isBalanceVisible: Boolean) {
        if (isBalanceVisible) {
            showBalancePix()
        } else {
            hideBalancePix()
        }
    }

    private fun showBalancePix() {
        _balance.postValue(balanceValue)
    }

    private fun hideBalancePix() {
        _balance.postValue(HIDDENVALUE)
    }

    companion object {
        private const val HIDDENVALUE = "* * * *"
    }

}
