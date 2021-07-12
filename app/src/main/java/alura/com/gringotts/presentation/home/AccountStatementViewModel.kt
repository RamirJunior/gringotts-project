package alura.com.gringotts.presentation.home
import AccountStatementRepository
import alura.com.gringotts.data.models.home.Transaction
import alura.com.gringotts.data.models.home.TransactionDateItem
import alura.com.gringotts.data.models.home.TransactionItem
import alura.com.gringotts.data.models.home.TransactionListItem
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

    private var currentTransactionsList = MutableLiveData<List<TransactionListItem>>()
    private val _accountStatementError = MutableLiveData<String>()
    val accountStatementError: LiveData<String> = _accountStatementError

    private fun getAccountStatement(
        initialDate: String,
        finalDate: String,
    ) {
        viewModelScope.launch {
            try {
                val response =
                    accountStatementRepository.getAccountStatement(initialDate, finalDate)
               currentTransactionsList.postValue(getTransactionsSegmentedList(response).toList())
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    _accountStatementError.postValue("Sem acesso a internet")
                else _accountStatementError
                    .postValue("Erro desconhecido ao recuperar o extrato")
            }
        }
    }

    private fun getTransactionsSegmentedList(response: List<Transaction>): List<TransactionListItem> {
        val transactionsMap = TreeMap<String, List<Transaction>>()
        val segmentedList: MutableList<TransactionListItem> = mutableListOf()
        for (i in response) {
            val currentList = transactionsMap[i.date] ?: listOf()
            transactionsMap[i.date] = currentList.plus(i)
        }
        for(date in transactionsMap.keys) {
            segmentedList.add(
                TransactionDateItem(
                    getDateFromString(date)
                )
            )
            for(transaction in transactionsMap[date]!!) {
                segmentedList.add(
                    TransactionItem(
                        transaction
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
        getAccountStatement(formatDate(currentDate.time), formatDate(sevenDaysAgo.time))
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
