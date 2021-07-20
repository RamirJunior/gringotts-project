package alura.com.gringotts.data.models.home

class TransactionDateItem(val transactionDate: TransactionDate) : TransactionListItem() {
    override fun getItemType(): Int = TRANSACTION_HEADER
}
