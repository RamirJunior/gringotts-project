package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.models.pix_transference.PixValidation
import alura.com.gringotts.data.models.pix_transference.UserPix
import alura.com.gringotts.data.repositories.pix_transference.PixRepository
import alura.com.gringotts.presentation.auxiliar.DateHelper
import alura.com.gringotts.presentation.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import java.util.*

class ConfirmationPixViewModel(
    private val pix: Pix,
    private val pixRepository: PixRepository
) : ViewModel() {

    private val _goToPixFinishedFragment = SingleLiveEvent<Unit>()
    val goToPixFinishedFragment: LiveData<Unit> = _goToPixFinishedFragment
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _pixUpdate = MutableLiveData<Pix>()
    val pixUpdate: LiveData<Pix> = _pixUpdate
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date
    private val _pixDate = MutableLiveData<String>()
    val pixDate: LiveData<String> = _pixDate
    private val _pixDateInMillis = MutableLiveData<Long>()
    val pixDateInMillis: LiveData<Long> = _pixDateInMillis
    private val _pixError = MutableLiveData<String>()
    val pixError: LiveData<String> = _pixError

    private lateinit var pixToken: String

    init {
        getDate()
    }

    private fun getDate() {
        val currentDate = Calendar.getInstance()
        _pixDate.postValue(DateHelper.formatDate(currentDate.time))
        _pixDateInMillis.postValue(currentDate.timeInMillis)
    }

    fun onClickPositiveDatePicker(timeInMillis: Long) {
        val newDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        _pixDateInMillis.postValue(timeInMillis)
        newDate.timeInMillis = timeInMillis
        _pixDate.postValue(DateHelper.formatDate(newDate.time, true))
    }

    fun validationPix() {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val response = pixRepository.pixValidationData(
                    PixValidation(
                        pix.receiverEmail,
                        TYPE_EMAIL,
                        pix.message,
                        pix.pixValue,
                        _pixDate.value!!
                    )
                )
                pixToken = response.pixToken
                updatePixValues(
                    response.user,
                    response.pix,
                    response.description,
                    response.organization,
                    response.pixValue,
                    response.date
                )
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    _pixError.postValue("Verifique sua conexão de internet.")
                else
                    _pixError.postValue("Erro desconhecido ao validar a transação !")
            }
            _loading.postValue(false)
        }
    }

    fun confirmPix() {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val response = pixRepository.pixConfirmData(pixToken)
                updatePixValues(
                    response.user,
                    response.pix,
                    response.description,
                    response.organization,
                    response.pixValue,
                    response.date
                )
                _goToPixFinishedFragment.postValue(Unit)
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    _pixError.postValue("Verifique sua conexão de internet.")
                else
                    _pixError.postValue("Erro desconhecido ao confirmar a transação !")
            }
            _loading.postValue(false)
        }
    }

    private fun updatePixValues(
        user: UserPix,
        email: String,
        description: String?,
        organization: String,
        pixValue: String,
        date: String
    ) {
        pix.name = user.firstName + " " + user.lastName
        pix.receiverEmail = email
        pix.message = description.orEmpty()
        pix.institution = organization
        pix.pixValue = pixValue.toDouble()
        pix.date = date
        _pixUpdate.postValue(pix)
    }

    companion object {
        private const val TYPE_EMAIL = "email"
    }

}
