package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.presentation.auxiliar.SingleLiveEvent
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InsertEmailPixViewModel(private val pix: Pix) : ViewModel() {

    private val _invalidEmailError = MutableLiveData<String?>()
    val invalidEmailError: LiveData<String?> = _invalidEmailError
    private val _goToInsertDescriptionScreen = SingleLiveEvent<Unit>()
    val goToInsertDescriptionScreen: LiveData<Unit> = _goToInsertDescriptionScreen
    private val _isButtonEnable = SingleLiveEvent<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable


    var currentEmail: String = ""

    init {
        _isButtonEnable.postValue(false)
    }

    fun insertEmail(newEmail: String){
        if(newEmail.length > 1){
            _isButtonEnable.postValue(true)
        }
        currentEmail = newEmail
    }


    fun onInsertEmailButtonClicked() {
        if (isEmailValid()) {
            pix.receiverEmail = currentEmail
            _goToInsertDescriptionScreen.postValue(Unit)
            _invalidEmailError.postValue(null)
        } else {
            _invalidEmailError.postValue("Insira um e-mail válido.")
        }
    }

    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches()
    }

}
