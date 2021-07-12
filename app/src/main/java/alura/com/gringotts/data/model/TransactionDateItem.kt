package alura.com.gringotts.data.model

import java.util.Date

class TransactionDateItem(val date: Date, newType: Int): TransactionListItem() {
    private val itemType: Int = newType
    override fun getItemType(): Int {
        return itemType
    }
}
