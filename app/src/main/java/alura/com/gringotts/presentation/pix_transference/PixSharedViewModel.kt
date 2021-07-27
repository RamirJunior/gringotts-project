package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.models.pix_transference.PixConfirmation
import alura.com.gringotts.data.repositories.pix_transference.ConfirmationRepository
import alura.com.gringotts.presentation.home.auxiliar.DateHelper.formatDate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class PixSharedViewModel(private val confirmationRepository: ConfirmationRepository) : ViewModel() {

    private val pix: Pix = Pix()

    fun saveEmail(newEmail: String) {
        pix.receiverEmail = newEmail
    }

    fun savePixValue(newPixValue: Float) {
        pix.pixValue = newPixValue
    }

    fun saveName(newName: String) {//API
        pix.receiverName = newName
    }

    fun saveMessage(newMessage: String) {
        pix.message = newMessage
    }

    fun saveInstitution(newInstitution: String) {//API
        pix.institution = newInstitution
    }

    fun saveDate(newDate: Date) {
        pix.date = newDate
    }

    suspend fun confirmPix() {
        confirmationRepository.pixConfirmationData(
            PixConfirmation(
                pix.receiverEmail,
                TYPE_EMAIL,
                pix.message,
                pix.pixValue.toDouble(),
                pix.date.toString()
            )
        )
    }

    fun getPix(): Pix = pix

    companion object {
        private const val TYPE_EMAIL = "email"
    }
}
