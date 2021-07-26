//package alura.com.gringotts.presentation.pix_transference
//
//import alura.com.gringotts.data.repositories.pix_transference.PixRepository
//import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
//import android.app.Activity
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//
//class ConfirmationPixViewModel (private val pixRepository: PixRepository) : ViewModel() {
//
//    private val _goToPixFinishedFragment = SingleLiveEvent<String>()
//    val goToPixFinishedFragment: LiveData<String> = _goToPixFinishedFragment
//
//}