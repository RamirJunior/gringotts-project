package alura.com.gringotts.data.model

class TransactionItem(
    val transaction: Transaction,
    newItemType: Int
) : TransactionListItem() {
    private val itemType = newItemType
    override fun getItemType(): Int {
        return itemType
    }
}
