package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.session.SessionManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InsertOptionalDescriptionPixViewModel(private val sessionManager: SessionManager) :
    ViewModel() {

    private var currentDescription: String = ""
    private val _invalidDescription = MutableLiveData<String>()
    val invalidDescription: LiveData<String> = _invalidDescription
    private val _validDescription = MutableLiveData<Unit>()
    val validDescription: LiveData<Unit> = _validDescription

    fun setDescription(value: String) {
        currentDescription = value
    }

    fun getDescription() = currentDescription

    fun onInsertDescriptionButtonClicked() {
        if (isDescriptionValid()) {
            _validDescription.postValue(Unit)
        } else {
            _invalidDescription.postValue("Tamanho inválido")
        }
    }

    private fun isDescriptionValid(): Boolean {
        return true
    }

}
