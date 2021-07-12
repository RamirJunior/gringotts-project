package alura.com.gringotts.data.models.home

class TransactionItem(
    val transaction: Transaction,
) : TransactionListItem() {
    private val itemType = TRANSACTION_ITEM
    override fun getItemType(): Int {
        return itemType
    }
}
