package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.session.SessionManager
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InsertEmailPixViewModel(private val sessionManager: SessionManager, private val pix : Pix) : ViewModel() {

    private val _invalidEmailError = MutableLiveData<String?>()
    val invalidEmailError: LiveData<String?> = _invalidEmailError
    private val _goToInsertDescriptionScreen = SingleLiveEvent<String>()
    val goToInsertDescriptionScreen: LiveData<String> = _goToInsertDescriptionScreen

    var currentEmail: String = ""

    fun onInsertEmailButtonClicked() {
        if (isEmailValid()) {
            pix
            _goToInsertDescriptionScreen.postValue(currentEmail)
            _invalidEmailError.postValue(null)
        } else if (!currentEmail.contains("@")) {
            _invalidEmailError.postValue("* E-mail inválido.")
        } else if (currentEmail.contains(" ")) {
            _invalidEmailError.postValue("* Não pode conter espaços em branco.")
        }
    }

    private fun isEmailValid(): Boolean {
        return (currentEmail.contains("@") && !(currentEmail.contains(" ")))
    }

}
