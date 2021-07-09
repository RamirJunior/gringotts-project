package alura.com.gringotts.data.model

class TransactionItem(
    val status: String,
    val time: String,
    val type: String,
    val date: String,
    val value: String,
    val typeDescription: String
) : TransactionListItem() {
    override fun getType(): Int {
        TODO("Not yet implemented")
    }

}