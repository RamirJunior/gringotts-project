package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.session.SessionManager
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InsertEmailPixViewModel(private val sessionManager: SessionManager) : ViewModel() {
    //////// SlingleLiveEvent ///////////
    private val uploadData = SingleLiveEvent<String>()
    fun getUploadData(): SingleLiveEvent<String> {
        return uploadData
    }

    /////////////////////////////////////
    private val _invalidEmail = MutableLiveData<String>()
    val invalidEmail: LiveData<String> = _invalidEmail
    private val _validEmail = MutableLiveData<Unit>()
    val validEmail: LiveData<Unit> = _validEmail

    var currentEmail: String = ""

    fun onInsertEmailButtonClicked() {
        if (isEmailValid()) {
            _validEmail.postValue(Unit)
        } else {
            _invalidEmail.postValue("e-mail inválido")
        }
    }

    private fun isEmailValid(): Boolean {
        return (currentEmail.contains("@") && !(currentEmail.contains(" ")))
    }
}
