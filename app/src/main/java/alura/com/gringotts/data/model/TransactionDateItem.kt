package alura.com.gringotts.data.model

import java.util.Date

class TransactionDateItem(val date: Date): TransactionListItem() {
    private val itemType: Int = TRANSACTION_HEADER
    override fun getItemType(): Int {
        return itemType
    }
}
