package alura.com.gringotts.presentation.home

import alura.com.gringotts.data.model.Transaction
import alura.com.gringotts.data.model.TransactionDateItem
import alura.com.gringotts.data.model.TransactionItem
import alura.com.gringotts.data.model.TransactionListItem
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

    fun getTransactionsSegmentedList(response: List<Transaction>): List<TransactionListItem> {
        val transactionsMap = TreeMap<String, List<Transaction>>()
        val segmentedList: List<TransactionListItem> = listOf()
        for (i in response) {
            val currentList = transactionsMap[i.date] ?: listOf<Transaction>()
            transactionsMap[i.date] = currentList.plus(i)
        }
        for(date in transactionsMap.keys) {
            segmentedList.plusElement(
                TransactionDateItem(
                    getDateFromString(date), TransactionListItem.TRANSACTION_HEADER
                )
            )
            for(transaction in transactionsMap[date]!!) {
                segmentedList.plusElement(
                    TransactionItem(
                        transaction,
                        TransactionListItem.TRANSACTION_ITEM
                    )
                )
            }
        }
        return segmentedList
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

    private fun getDateFromString(dateString: String): Date {
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.US)
        return formatter.parse(dateString)!!
    }

    companion object {
        private const val MILLIS_DAY: Long = 86400000
        private const val DATE_FORMAT: String = "dd/MM/yyyy"
    }

}
