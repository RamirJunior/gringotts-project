package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.session.SessionManager
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InsertOptionalDescriptionPixViewModel(private val sessionManager: SessionManager) :
    ViewModel() {

    private val _invalidDescription = SingleLiveEvent<String>()
    val invalidDescription: LiveData<String> = _invalidDescription
    private val _validDescription = MutableLiveData<Unit>()
    val validDescription: LiveData<Unit> = _validDescription

    var currentDescription: String = ""

    fun onInsertDescriptionButtonClicked() {
        if (isDescriptionValid()) {
            _validDescription.postValue(Unit)
        } else {
            _invalidDescription.postValue("Tamanho inválido")
        }
    }

    private fun isDescriptionValid(): Boolean {
        return currentDescription.length <= 72
    }
}
