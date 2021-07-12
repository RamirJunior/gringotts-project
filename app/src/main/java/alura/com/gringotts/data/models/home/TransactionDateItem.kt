package alura.com.gringotts.data.models.home

import java.util.*

class TransactionDateItem(val date: Date) : TransactionListItem() {
    private val itemType: Int = TRANSACTION_HEADER
    override fun getItemType(): Int {
        return itemType
    }
}
