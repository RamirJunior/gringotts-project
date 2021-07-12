package alura.com.gringotts.data.model

class TransactionItem(
    val status: String,
    val time: String,
    val type: String,
    val date: String,
    val value: String,
    val typeDescription: String,
    newItemType: Int
) : TransactionListItem() {
    private val itemType = newItemType
    override fun getItemType(): Int {
        return itemType
    }
}
