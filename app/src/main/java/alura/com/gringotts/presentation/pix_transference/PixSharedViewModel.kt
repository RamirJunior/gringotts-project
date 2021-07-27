package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.models.pix_transference.PixConfirmation
import alura.com.gringotts.data.repositories.pix_transference.ConfirmationRepository
import androidx.lifecycle.ViewModel
import java.util.*

class PixSharedViewModel(private val confirmationRepository: ConfirmationRepository) : ViewModel() {

    private val pix: Pix = Pix()

    fun saveEmail(newEmail: String) { //salvo da tela
        pix.receiverEmail = newEmail
    }

    fun savePixValue(newPixValue: Float) {//salvo da tela
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

    fun saveDate(newDate: Date) {//salvo da tela
        pix.date = newDate
    }

    suspend fun confirmPix(){
        confirmationRepository.pixConfirmationData(PixConfirmation(pix.receiverEmail, TYPE_EMAIL, pix.message, pix.pixValue.toDouble(), pix.date.toString()))
    }

    fun getPix(): Pix = pix

    companion object{
        private const val TYPE_EMAIL = "email"
    }
}
