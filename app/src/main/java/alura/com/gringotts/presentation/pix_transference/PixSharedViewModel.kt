package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.models.pix_transference.PixValidation
import alura.com.gringotts.data.models.pix_transference.UserPix
import alura.com.gringotts.data.repositories.pix_transference.PixRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PixSharedViewModel(private val pixRepository: PixRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name
    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description
    private val _value = MutableLiveData<String>()
    val value: LiveData<String> = _value
    private val _institution = MutableLiveData<String>()
    val institution: LiveData<String> = _institution
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val pix: Pix = Pix()

    fun saveEmail(newEmail: String) {
        pix.receiverEmail = newEmail
    }

    fun savePixValue(newPixValue: Double) {
        pix.pixValue = newPixValue
    }

    fun saveMessage(newMessage: String) {
        pix.message = newMessage
    }

    fun saveDate(newDate: String) {
        pix.date = newDate
    }

    fun validationPix() {
        _loading.postValue(true)
        viewModelScope.launch {
            val response = pixRepository.pixValidationData(
                PixValidation(
                    pix.receiverEmail,
                    TYPE_EMAIL,
                    pix.message,
                    pix.pixValue,
                    pix.date
                )
            )
            updatePixValues(
                response.user,
                response.pix,
                response.description,
                response.organization,
                response.pixValue,
                response.date
            )
        }
        _loading.postValue(false)
    }

    fun confirmPix(){
        _loading.postValue(true)
        viewModelScope.launch {
            val response = pixRepository.pixConfirmData()
            updatePixValues(
                response.user,
                response.pix,
                response.description,
                response.organization,
                response.pixValue,
                response.date
            )
        }
        _loading.postValue(false)
    }

    private fun updatePixValues(
        user: UserPix,
        pix: String,
        description: String,
        organization: String,
        pixValue: String,
        date: String
    ) {
        _name.postValue(user.firstName + " " + user.lastName)
        _email.postValue(pix)
        _description.postValue(description)
        _institution.postValue(organization)
        _value.postValue(pixValue)
        _date.postValue(date)
    }

    companion object {
        private const val TYPE_EMAIL = "email"
    }

}
