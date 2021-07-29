package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.session.SessionManager
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class InsertOptionalDescriptionPixViewModel(
    private val pix: Pix
) :
    ViewModel() {

    private val _goToPixValueFragment = SingleLiveEvent<Boolean>()
    val goToPixValueFragment: LiveData<Boolean> = _goToPixValueFragment

    var currentDescription: String = ""

    fun onInsertDescriptionButtonClicked() {
        pix.message = currentDescription
        _goToPixValueFragment.postValue(true)
    }
}
