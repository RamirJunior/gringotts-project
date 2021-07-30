package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.repositories.pix_transference.PixActualAccountValueRepository
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PixValueViewModel(
    private val pix: Pix,
    private val pixActualAccountValueRepository: PixActualAccountValueRepository
) :
    ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _balance = MutableLiveData<String>()
    val balance: LiveData<String> = _balance
    private val _apiError = MutableLiveData<String>()
    val apiError: LiveData<String> = _apiError
    private val _invalidValueError = MutableLiveData<String?>()
    val invalidValueError: LiveData<String?> = _invalidValueError
    private val _goToConfirmationPixFragment = SingleLiveEvent<Boolean>()
    val goToConfirmationPixFragment: LiveData<Boolean> = _goToConfirmationPixFragment

    private val _hideButtonText = MutableLiveData<String>()
    val hideButtonText: LiveData<String> = _hideButtonText

    private val _hideButtonValue = MutableLiveData<Boolean>()
    val hideButtonValue: LiveData<Boolean> = _hideButtonValue

    private var balanceValue: Double=0.0

    var pixValue: Double = 0.0

    init {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                balanceValue=pixActualAccountValueRepository.balanceData()
                _balance.postValue(balanceValue.toString())
                _hideButtonValue.postValue(pixActualAccountValueRepository.getHideBalanceStatePix())
            } catch (e: Exception) {
                _apiError.postValue("Sem acesso a internet")
            }
            _loading.postValue(false)
        }
    }

    fun onValueButtonClicked() {
        if (pixValue <= balanceValue && pixValue > 0) {
            _goToConfirmationPixFragment.postValue(true)
            _invalidValueError.postValue(null)
        } else if (pixValue> balanceValue) {
            _invalidValueError.postValue("Valor da transferência maior que saldo")
        } else if (pixValue <= 0) {
            _invalidValueError.postValue("Transferência deve ser de pelo menos 0,01.")
        }
    }

    fun hideBalanceButtonClickedPix() {
        val newCurrentBalanceVisibilityStatus = !_hideButtonValue.value!!
        if(newCurrentBalanceVisibilityStatus){
            _balance.postValue(HIDDENVALUE)
        }
        else{
            _balance.postValue(balanceValue.toString())
        }
        pixActualAccountValueRepository.saveHideBalanceStatePix(newCurrentBalanceVisibilityStatus)
        _hideButtonValue.postValue(newCurrentBalanceVisibilityStatus)
    }

    private fun hideBalancePix() {
        _hideValueString.postValue(HIDDENVALUE)
        _hideButtonText.postValue("Mostrar")
    }

    companion object {
        private const val HIDDENVALUE = "* * * *"
    }

}
