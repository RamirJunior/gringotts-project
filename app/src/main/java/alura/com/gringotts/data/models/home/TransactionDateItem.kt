package alura.com.gringotts.data.models.home

class TransactionDateItem(val day: String, val month: String) : TransactionListItem() {
    private val itemType: Int = TRANSACTION_HEADER
    override fun getItemType(): Int {
        return itemType
    }
}
