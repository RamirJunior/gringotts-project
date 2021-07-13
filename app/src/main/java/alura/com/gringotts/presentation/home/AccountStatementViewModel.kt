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

    private lateinit var transactionList: List<Transaction>
    private val _currentTransactionsList = MutableLiveData<List<TransactionListItem>>()
    val currentTransactionsList: LiveData<List<TransactionListItem>> = _currentTransactionsList
    private val _accountStatementError = MutableLiveData<String>()
    val accountStatementError: LiveData<String> = _accountStatementError
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        getAccountStatement(DEFAULT_RANGE)
    }

    private fun getTransactionList(
        initialDate: String,
        finalDate: String,
    ) {
        viewModelScope.launch {
            try {
                val response =
                    accountStatementRepository.getAccountStatement(initialDate, finalDate)
                transactionList = response
                _currentTransactionsList.postValue(
                    getTransactionsSegmentedList(transactionList)
                )
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    _accountStatementError.postValue("Sem acesso a internet")
                else _accountStatementError
                    .postValue("Erro desconhecido ao recuperar o extrato")
            }
        }
    }

    private fun getTransactionsSegmentedList
                (response: List<Transaction>): List<TransactionListItem> {
        val transactionsMap = TreeMap<String, List<Transaction>>()
        val segmentedList: MutableList<TransactionListItem> = mutableListOf()
        for (i in response) {
            val currentList = transactionsMap[i.date] ?: listOf()
            transactionsMap[i.date] = currentList.plus(i)
        }
        for (date in transactionsMap.keys) {
            segmentedList.add(
                TransactionDateItem(
                    getDateFromString(date)
                )
            )
            for (transaction in transactionsMap[date]!!) {
                segmentedList.add(
                    TransactionItem(
                        transaction
                    )
                )
            }
        }
        return segmentedList.toList()
    }

    private fun getAccountStatement(range: Int) {
        val currentDate = Calendar.getInstance()
        val sevenDaysAgo = Calendar.getInstance()
        sevenDaysAgo.timeInMillis = (currentDate.timeInMillis - range * MILLIS_DAY)
        getTransactionList(formatDate(currentDate.time), formatDate(sevenDaysAgo.time))
    }

    private fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.US)
        return formatter.format(date)
    }

    private fun getDateFromString(dateString: String): Date {
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.US)
        return formatter.parse(dateString)!!
    }

    fun changeRange(newRange: Int) {
        getAccountStatement(newRange)
    }

    fun setAllTransactions(){
        getTransactionsSegmentedList(transactionList)
    }

    fun setOnlyEntries() {
        val filteredTransactions = mutableListOf<Transaction>()
        for (transaction in transactionList) {
            if (transaction.type == PAYMENT_FILTER) {
                filteredTransactions.add(transaction)
            }
        }
        getTransactionsSegmentedList(filteredTransactions.toList())
    }

    companion object {
        private const val MILLIS_DAY: Long = 86400000
        private const val DATE_FORMAT: String = "dd/MM/yyyy"
        private const val DEFAULT_RANGE: Int = 3
        private const val PAYMENT_FILTER: String = "Pagamento"
    }
}
