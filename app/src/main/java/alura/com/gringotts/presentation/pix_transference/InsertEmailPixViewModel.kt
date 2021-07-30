package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.session.SessionManager
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InsertEmailPixViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private val _invalidEmailError = MutableLiveData<String?>()
    val invalidEmailError: LiveData<String?> = _invalidEmailError
    private val _goToInsertDescriptionScreen = SingleLiveEvent<String>()
    val goToInsertDescriptionScreen: LiveData<String> = _goToInsertDescriptionScreen

    var currentEmail: String = ""

    fun onInsertEmailButtonClicked() {
        if (isEmailValid(currentEmail)) {
            _goToInsertDescriptionScreen.postValue(currentEmail)
            _invalidEmailError.postValue(null)
        } else if (!currentEmail.contains("@")) {
            _invalidEmailError.postValue("* E-mail inválido.")
        } else if (currentEmail.contains(" ")) {
            _invalidEmailError.postValue("* Não pode conter espaços em branco.")
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
