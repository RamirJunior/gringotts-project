package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.models.pix_transference.PixValidation
import alura.com.gringotts.data.models.pix_transference.UserPix
import alura.com.gringotts.data.repositories.pix_transference.PixRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PixSharedViewModel(private val pixRepository: PixRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val pix: Pix = Pix()

    fun saveEmail(newEmail: String) {
        pix.receiverEmail = newEmail
    }

    fun savePixValue(newPixValue: Double) {
        pix.pixValue = newPixValue
    }

    private fun saveName(newName: String) {
        pix.receiverName = newName
    }

    fun saveMessage(newMessage: String) {
        pix.message = newMessage
    }

    private fun saveInstitution(newInstitution: String) {
        pix.institution = newInstitution
    }

    fun saveDate(newDate: String) {
        pix.date = newDate
    }

    fun getPix(): Pix = pix

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
            Log.e("Valor",pix.pixValue.toString())
            Log.e("Organizacao",pix.institution)
            Log.e("Valor",pix.receiverName)
            Log.e("Valor",pix.receiverEmail)
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
        saveName(user.firstName + " " + user.lastName)
        saveEmail(pix)
        saveMessage(description)
        saveInstitution(organization)
        savePixValue(pixValue.toDouble())
        saveDate(date)
    }

    companion object {
        private const val TYPE_EMAIL = "email"
    }

}
