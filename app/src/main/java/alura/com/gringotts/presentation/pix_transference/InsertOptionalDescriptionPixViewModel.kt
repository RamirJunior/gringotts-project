package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.session.SessionManager
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class InsertOptionalDescriptionPixViewModel(private val sessionManager: SessionManager) :
    ViewModel() {

    private val _goToPixValueFragment = SingleLiveEvent<String?>()
    val goToPixValueFragment: LiveData<String?> = _goToPixValueFragment

    var currentDescription: String = ""

    fun onInsertDescriptionButtonClicked() {
        _goToPixValueFragment.postValue(currentDescription)
    }
}
