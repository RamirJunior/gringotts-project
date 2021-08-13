package alura.com.gringotts.presentation.home

import AccountStatementRepository
import alura.com.gringotts.data.models.home.*
import alura.com.gringotts.presentation.auxiliar.DateHelper.formatDate
import alura.com.gringotts.presentation.auxiliar.DateHelper.getDateFromString
import alura.com.gringotts.presentation.auxiliar.DateHelper.getMonthString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import java.util.*

class AccountStatementViewModel(
    private val accountStatementRepository: AccountStatementRepository
) : ViewModel() {

    private val _currentTransactionsList = MutableLiveData<List<TransactionListItem>>()
    val currentTransactionsList: LiveData<List<TransactionListItem>> = _currentTransactionsList
    private val _accountStatementError = MutableLiveData<String>()
    val accountStatementError: LiveData<String> = _accountStatementError
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _showEmptyListPlaceHolder = MutableLiveData<Boolean>()
    val showEmptyListPlaceHolder: LiveData<Boolean> = _showEmptyListPlaceHolder

    var currentRange = DEFAULT_RANGE
    private lateinit var transactionList: List<Transaction>

    init {
        getAccountStatement(DEFAULT_RANGE)
    }

    private fun getAccountStatement(daysAgo: Int) {
        val currentDate = Calendar.getInstance()
        val daysAgoDate = Calendar.getInstance()
        daysAgoDate.add(Calendar.DAY_OF_MONTH, -daysAgo)
        getTransactionList(formatDate(currentDate.time), formatDate(daysAgoDate.time))
    }

    private fun getTransactionList(
        initialDate: String,
        finalDate: String,
    ) {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val response =
                    accountStatementRepository.getAccountStatement(initialDate, finalDate)
                transactionList = response
                mapToTransactionsSegmentedList(transactionList)
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    _accountStatementError.postValue("Verifique sua conexão de internet.")
                else _accountStatementError
                    .postValue("Erro desconhecido ao recuperar o extrato.")
            }
            _loading.postValue(false)
        }
    }

    private fun mapToTransactionsSegmentedList(response: List<Transaction>) {
        val segmentedList: MutableList<TransactionListItem> = mutableListOf()
        var lastDate: Date? = null
        for (transaction in response) {
            if (lastDate != getDateFromString(transaction.date)) {
                lastDate = getDateFromString(transaction.date)
                val calendar = Calendar.getInstance()
                calendar.time = lastDate
                segmentedList.add(TransactionDateItem(TransactionDate(getMonthString(calendar))))
            }
            segmentedList.add(TransactionItem(transaction))
        }
        _currentTransactionsList.postValue(segmentedList)
        _showEmptyListPlaceHolder.postValue(segmentedList.isEmpty())
    }

    fun changeRange(newRange: Int) {
        currentRange = newRange
        getAccountStatement(newRange)
    }

    fun setAllTransactions() {
        mapToTransactionsSegmentedList(transactionList)
    }

    fun setOnlyEntries() {
        mapToTransactionsSegmentedList(transactionList.filter { it.type == PAYMENT_FILTER })
    }

    fun setWithdraw() {
        mapToTransactionsSegmentedList(transactionList.filter { it.type == EXPENSE_FILTER })
    }

    companion object {
        private const val DEFAULT_RANGE: Int = 7
        private const val EXPENSE_FILTER: String = "Despesa"
        private const val PAYMENT_FILTER: String = "Pagamento"
    }

}
