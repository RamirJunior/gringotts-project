package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.repositories.pix_transference.ConfirmationRepository
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ConfirmationPixViewModel() :
    ViewModel() {

    private val _goToPixFinishedFragment = SingleLiveEvent<String>()
    val goToPixFinishedFragment: LiveData<String> = _goToPixFinishedFragment

}
