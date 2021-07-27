package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.models.pix_transference.PixValidation
import alura.com.gringotts.data.repositories.pix_transference.PixRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PixSharedViewModel(private val pixRepository: PixRepository) : ViewModel() {

    private val pix: Pix = Pix()

    fun saveEmail(newEmail: String) { //salvo da tela
        pix.receiverEmail = newEmail
    }

    fun savePixValue(newPixValue: Double) {//salvo da tela
        pix.pixValue = newPixValue
    }

    fun saveName(newName: String) {//API
        pix.receiverName = newName
    }

    fun saveMessage(newMessage: String) {//salvo da tela
        pix.message = newMessage
    }

    fun saveInstitution(newInstitution: String) {//API
        pix.institution = newInstitution
    }

    fun saveDate(newDate: String) {//salvo da tela
        pix.date = newDate
    }

    fun getPix(): Pix = pix

    fun validationPix() {
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
            Log.d("Teste Api", response.toString())
        }
    }


    fun confirmPix() {
        viewModelScope.launch {
            val response = pixRepository.pixConfirmData()
            Log.d("Teste Api 2", response.toString())
        }
    }

    companion object {
        private const val TYPE_EMAIL = "email"
    }

}
