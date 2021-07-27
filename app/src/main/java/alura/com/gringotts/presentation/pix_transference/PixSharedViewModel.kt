package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.models.pix_transference.PixConfirmation
import alura.com.gringotts.data.models.pix_transference.PixResponse
import alura.com.gringotts.data.repositories.pix_transference.ConfirmationRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.*

class PixSharedViewModel(private val confirmationRepository: ConfirmationRepository) : ViewModel() {

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

    fun confirmPix(): PixResponse? {
        var response : PixResponse? = null
        viewModelScope.launch {
            response = confirmationRepository.pixConfirmationData(
                PixConfirmation(
                    pix.receiverEmail,
                    TYPE_EMAIL,
                    pix.message,
                    pix.pixValue.toDouble(),
                    pix.date.toString()
                )
            )
        }
        return response
    }

    companion object{
        private const val TYPE_EMAIL = "email"
    }

}
