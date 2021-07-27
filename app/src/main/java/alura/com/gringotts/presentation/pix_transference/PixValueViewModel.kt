package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.repositories.pix_transference.PixRepository
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PixValueViewModel(private val pixRepository: PixRepository) : ViewModel() {

    private val _balance = MutableLiveData<String>()
    val balance: LiveData<String> = _balance
    private val _apiError = MutableLiveData<String>()
    val apiError: LiveData<String> = _apiError
    private val _invalidValueError = MutableLiveData<String?>()
    val invalidValueError: LiveData<String?> = _invalidValueError
    private val _goToConfirmationPixFragment = SingleLiveEvent<Double>()
    val goToConfirmationPixFragment: LiveData<Double> = _goToConfirmationPixFragment

    var pixValueToFloat: Double = 0.0


    init {
        viewModelScope.launch {
            try {
                _balance.postValue(pixRepository.balanceData().toString())
            } catch (e: Exception) {
                _apiError.postValue("Sem acesso a internet")
            }
        }
    }

    fun onValueButtonClicked() {
        if (pixValueToFloat <= balance.value!!.toDouble() && pixValueToFloat > 0) {
            _goToConfirmationPixFragment.postValue(pixValueToFloat)
            _invalidValueError.postValue(null)
        } else if (pixValueToFloat > balance.value!!.toDouble()) {
            _invalidValueError.postValue("Valor da transferência maior que saldo")
        } else if (pixValueToFloat <= 0) {
            _invalidValueError.postValue("Transferência deve ser de pelo menos 0,01.")
        }
    }

}
