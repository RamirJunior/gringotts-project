package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.repositories.pix_transference.PixRepository
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

    init {
        viewModelScope.launch {
            try {
                val value = pixRepository.balanceData()
                _balance.postValue(balance.toString())

            } catch (e: Exception) {
                _apiError.postValue("Sem acesso a internet")
            }
        }
    }
}
