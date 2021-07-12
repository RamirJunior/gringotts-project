package alura.com.gringotts.data.models.home

abstract class TransactionListItem {
    abstract fun getItemType(): Int

    companion object {
        const val TRANSACTION_HEADER = 0
        const val TRANSACTION_ITEM = 1
    }
}
