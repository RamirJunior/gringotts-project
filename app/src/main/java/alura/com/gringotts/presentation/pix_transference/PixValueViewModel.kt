package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.repositories.pix_transference.PixRepository
import alura.com.gringotts.presentation.auxiliar.NumberFormatHelper
import alura.com.gringotts.presentation.auxiliar.NumberFormatHelper.formatDoubleToTwoFractionDigits
import alura.com.gringotts.presentation.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PixValueViewModel(
    private val pix: Pix,
    private val pixRepository: PixRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _balance = MutableLiveData<String>()
    val balance: LiveData<String> = _balance
    private val _apiError = MutableLiveData<String>()
    val apiError: LiveData<String> = _apiError
    private val _invalidValueError = MutableLiveData<String?>()
    val invalidValueError: LiveData<String?> = _invalidValueError
    private val _goToConfirmationPixFragment = SingleLiveEvent<Unit>()
    val goToConfirmationPixFragment: LiveData<Unit> = _goToConfirmationPixFragment

    private val _hideButtonValue = MutableLiveData<Boolean>()
    val hideButtonValue: LiveData<Boolean> = _hideButtonValue

    private var balanceValue: Double = 0.0

    private var pixValue: Double = 0.0

    init {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                balanceValue = pixRepository.balanceData()
                _balance.postValue(formatDoubleToTwoFractionDigits(balanceValue))
                _hideButtonValue.postValue(pixRepository.getBalancePixStateVisibility())
            } catch (e: Exception) {
                _apiError.postValue("Sem acesso a internet")
            }
            _loading.postValue(false)
        }
    }

    fun onValueButtonClicked(newValue: String) {
        if (newValue.isEmpty()) return
        pixValue = NumberFormatHelper.formatStringToDouble(newValue)
        if (pixValue <= balanceValue && pixValue > 0) {
            pix.pixValue = pixValue
            _goToConfirmationPixFragment.postValue(Unit)
            _invalidValueError.postValue(null)
        } else if (pixValue > balanceValue) {
            _invalidValueError.postValue("Valor da transferência maior que saldo")
        } else if (pixValue <= 0) {
            _invalidValueError.postValue("Transferência deve ser de pelo menos 0,01.")
        }
    }

    fun hideBalanceButtonClickedPix() {
        val newCurrentBalanceVisibilityStatus = !_hideButtonValue.value!!
        pixRepository.saveBalancePixStateVisibility(
            newCurrentBalanceVisibilityStatus
        )
        _hideButtonValue.postValue(newCurrentBalanceVisibilityStatus)
    }

}
