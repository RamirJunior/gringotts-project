package alura.com.gringotts.presentation

import alura.com.gringotts.data.repositories.AccountStatementRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class AccountStatementViewModel(private val accountStatementRepository: AccountStatementRepository) :
    ViewModel() {
    private val dataAtual: String = ""

    fun getCalendar() {
        val currentDate = Calendar.getInstance()
        val sevenDaysAgo = Calendar.getInstance()
        sevenDaysAgo.timeInMillis = (currentDate.timeInMillis - 7 * MILLIS_DAY)
        Log.e("aaaa", formatDate(sevenDaysAgo.time))
    }

    private fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        return formatter.format(date)
    }

    companion object {
        private const val MILLIS_DAY: Long = 86400000
    }
}