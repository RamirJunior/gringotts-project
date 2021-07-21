package alura.com.gringotts.data.models.home

class TransactionItem(val transaction: Transaction) : TransactionListItem() {
    override fun getItemType(): Int = TRANSACTION_ITEM
}
