package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.presentation.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class InsertOptionalDescriptionPixViewModel(
    private val pix: Pix
) : ViewModel() {

    private val _goToPixValueFragment = SingleLiveEvent<Unit>()
    val goToPixValueFragment: LiveData<Unit> = _goToPixValueFragment

    var currentDescription: String = ""

    fun onInsertDescriptionButtonClicked() {
        pix.message = currentDescription
        _goToPixValueFragment.postValue(Unit)
    }
}
