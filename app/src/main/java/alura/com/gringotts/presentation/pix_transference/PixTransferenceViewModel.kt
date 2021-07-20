package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.session.SessionManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PixTransferenceViewModel(private val sessionManager: SessionManager) : ViewModel() {
    private var currentEmail: String = ""
    private val _invalidEmail = MutableLiveData<String>()
    val invalidEmail: LiveData<String> = _invalidEmail
    private val _validEmail = MutableLiveData<Unit>()
    val validEmail: LiveData<Unit> = _validEmail

    fun setEmail(value: String) {
        currentEmail = value
    }

    fun onInsertEmailButtonClicked() {
        if (isEmailValid()) {
            _validEmail.postValue(Unit)
        } else {
            _invalidEmail.postValue("e-mail invalido")
        }
    }

    private fun isEmailValid(): Boolean {
        return true
    }
}
