package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.session.SessionManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InsertEmailPixViewModel(private val sessionManager: SessionManager) : ViewModel() {
    //////// SlingleLiveEvent ///////////
//    private val uploadData = SingleLiveEvent<String>()
//    fun getUploadData(): SingleLiveEvent<String> {
//        return uploadData
//    }

    /////////////////////////////////////
    private val _invalidEmailError = MutableLiveData<String?>()
    val invalidEmailError: LiveData<String?> = _invalidEmailError
    private val _goToInsertDescriptionScreen = SingleLiveEvent<String>()
    val goToInsertDescriptionScreen: LiveData<String> = _goToInsertDescriptionScreen

    var currentEmail: String = ""

    fun onInsertEmailButtonClicked() {
        if (isEmailValid()) {
            _goToInsertDescriptionScreen.postValue(currentEmail)
            _invalidEmailError.postValue(null)
        } else {
            _invalidEmailError.postValue("e-mail inválido")
        }
    }

    private fun isEmailValid(): Boolean {
        return (currentEmail.contains("@") && !(currentEmail.contains(" ")))
    }
}
