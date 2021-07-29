package alura.com.gringotts.presentation.pix_transference

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.presentation.home.auxiliar.DateHelper
import alura.com.gringotts.presentation.pix_transference.auxiliar.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class ConfirmationPixViewModel(
    private val pix: Pix
) :
    ViewModel() {

    private val _goToPixFinishedFragment = SingleLiveEvent<String>()
    val goToPixFinishedFragment: LiveData<String> = _goToPixFinishedFragment

    private val _pixDate = MutableLiveData<String>()
    val pixDate: LiveData<String> = _pixDate
    private val _pixDateInMillis = MutableLiveData<Long>()
    val pixDateInMillis: LiveData<Long> = _pixDateInMillis


    init {
        getDate()
    }

    private fun getDate() {
        val currentDate = Calendar.getInstance()
        _pixDate.postValue(DateHelper.formatDate(currentDate.time))
        _pixDateInMillis.postValue(currentDate.timeInMillis)
    }

    fun positiveDataPicker(timeInMillis: Long) {
        if (timeInMillis > MaterialDatePicker.todayInUtcMilliseconds()) {
            val newDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            _pixDateInMillis.postValue(timeInMillis)
            newDate.timeInMillis = timeInMillis
            _pixDate.postValue(DateHelper.formatDate(newDate.time, true))
        }
    }

}
