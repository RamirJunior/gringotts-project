package alura.com.gringotts.presentation

import alura.com.gringotts.data.model.Transaction
import alura.com.gringotts.data.repositories.AccountStatementRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*

class AccountStatementViewModel
    (private val accountStatementRepository: AccountStatementRepository) : ViewModel() {

    private var currentTransactions: List<Transaction> = listOf()
    private val _accountStatementError = MutableLiveData<String>()
    val accountStatementError: LiveData<String> = _accountStatementError

    private suspend fun getAccountStatement(
        initialDate: String,
        finalDate: String,
    ) {
        viewModelScope.launch {
            try {
                val response =
                    accountStatementRepository.getAccountStatement(initialDate, finalDate)
                currentTransactions = response.transactions
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    _accountStatementError.postValue("Sem acesso a internet")
                else _accountStatementError
                    .postValue("Erro desconhecido ao recuperar o extrato")
            }
        }
    }


    fun getCalendar() {
        val currentDate = Calendar.getInstance()
        val sevenDaysAgo = Calendar.getInstance()
        sevenDaysAgo.timeInMillis = (currentDate.timeInMillis - 7 * MILLIS_DAY)
    }

    private fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.US)
        return formatter.format(date)
    }

    companion object {
        private const val MILLIS_DAY: Long = 86400000
        private const val DATE_FORMAT: String = "dd/MM/yyyy"
    }
}
