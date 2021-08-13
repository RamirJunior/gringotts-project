package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.presentation.auxiliar.InputValidationHelper.isEmailValid
import alura.com.gringotts.presentation.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InsertEmailPixViewModel(private val pix: Pix) : ViewModel() {

    private val _invalidEmailError = MutableLiveData<String?>()
    val invalidEmailError: LiveData<String?> = _invalidEmailError
    private val _goToInsertDescriptionScreen = SingleLiveEvent<Unit>()
    val goToInsertDescriptionScreen: LiveData<Unit> = _goToInsertDescriptionScreen
    private val _isButtonEnable = MutableLiveData(false)
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    private var currentEmail: String = ""

    fun insertEmail(newEmail: String) {
        _isButtonEnable.postValue(newEmail.isNotEmpty())
        currentEmail = newEmail
    }

    fun onInsertEmailButtonClicked() {
        if (isEmailValid(currentEmail)) {
            pix.receiverEmail = currentEmail
            _goToInsertDescriptionScreen.postValue(Unit)
            _invalidEmailError.postValue(null)
        } else {
            _invalidEmailError.postValue("Insira um e-mail válido.")
        }
    }

}
