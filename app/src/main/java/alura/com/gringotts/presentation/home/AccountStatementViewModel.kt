package alura.com.gringotts.presentation.home

import AccountStatementRepository
import alura.com.gringotts.data.models.home.*
import alura.com.gringotts.data.models.home.Date
import alura.com.gringotts.presentation.home.auxiliar.DataHelper.Companion.formatDate
import alura.com.gringotts.presentation.home.auxiliar.DataHelper.Companion.getDateFromString
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

    private lateinit var transactionList: List<Transaction>
    private val _currentTransactionsList = MutableLiveData<List<TransactionListItem>>()
    val currentTransactionsList: LiveData<List<TransactionListItem>> = _currentTransactionsList
    private val _accountStatementError = MutableLiveData<String>()
    val accountStatementError: LiveData<String> = _accountStatementError
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _isListVisible = MutableLiveData<Boolean>()
    val isListVisible: LiveData<Boolean> = _isListVisible
    var currentRange = DEFAULT_RANGE

    init {
        getAccountStatement(DEFAULT_RANGE)
    }

    private fun getAccountStatement(daysAgo: Int) {
        val currentDate = Calendar.getInstance()
        val sevenDaysAgo = Calendar.getInstance()
        sevenDaysAgo.add(Calendar.DAY_OF_MONTH, -daysAgo)
        getTransactionList(formatDate(currentDate.time), formatDate(sevenDaysAgo.time))
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
        val transactionsMap = TreeMap<String, List<Transaction>>()
        for (i in response) {
            val currentList = transactionsMap[i.date] ?: listOf()
            transactionsMap[i.date] = currentList.plus(i)
        }
        val segmentedList: List<TransactionListItem> = makeSegmentedList(transactionsMap)
        if (segmentedList.isEmpty()) {
            _isListVisible.postValue(false)
        } else {
            _currentTransactionsList.postValue(
                segmentedList.reversed()
            )
            _isListVisible.postValue(true)
        }
    }

    private fun makeSegmentedList(transactionsMap: TreeMap<String, List<Transaction>>)
            : List<TransactionListItem> {
        val segmentedList: MutableList<TransactionListItem> = mutableListOf()
        for (date in transactionsMap.keys) {
            val calendar = Calendar.getInstance()
            calendar.time = getDateFromString(date)
            for (transaction in transactionsMap[date]!!) {
                segmentedList.add(
                    TransactionItem(
                        transaction
                    )
                )
            }
            segmentedList.add(
                TransactionDateItem(
                    Date(
                        calendar.get(Calendar.DAY_OF_MONTH).toString(),
                        monthIntToString(calendar.get(Calendar.MONTH))
                    )
                )
            )
        }
        return segmentedList.toList()
    }

    private fun monthIntToString(monthInt: Int): String {
        when (monthInt) {
            1 -> {
                return "JAN"
            }
            2 -> {
                return "FEV"
            }
            3 -> {
                return "MAR"
            }
            4 -> {
                return "ABR"
            }
            5 -> {
                return "MAI"
            }
            6 -> {
                return "JUN"
            }
            7 -> {
                return "JUL"
            }
            8 -> {
                return "AGO"
            }
            9 -> {
                return "SET"
            }
            10 -> {
                return "OUT"
            }
            11 -> {
                return "NOV"
            }
            12 -> {
                return "DEZ"
            }
            else -> return ""
        }
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
