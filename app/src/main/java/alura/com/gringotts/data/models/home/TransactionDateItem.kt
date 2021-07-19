package alura.com.gringotts.data.models.home

class TransactionDateItem(val date: Date) : TransactionListItem() {
    override fun getItemType(): Int = TRANSACTION_HEADER
}
