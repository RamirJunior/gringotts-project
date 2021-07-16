package alura.com.gringotts.view.home.adapters

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.TransactionDateItem
import alura.com.gringotts.data.models.home.TransactionItem
import alura.com.gringotts.data.models.home.TransactionListItem
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TransactionListAdapter(private var transaction: List<TransactionListItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return transaction[position].getItemType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TransactionListItem.TRANSACTION_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.date_item, parent, false)
            DateViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_item, parent, false)
            TransactionViewHolder(view)
        }
    }

    fun setAdapterList( newList: List<TransactionListItem> ){
        transaction = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DateViewHolder) {
            val header: TransactionDateItem = transaction[position] as TransactionDateItem
            holder.bindHeader(header)
        } else if (holder is TransactionViewHolder) {
            val transactionItem: TransactionItem = transaction[position] as TransactionItem
            holder.bindList(transactionItem)
        }
    }

    override fun getItemCount(): Int {
        return transaction.size
    }

    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val day: TextView = itemView.findViewById(R.id.textview_day)
        private val month: TextView = itemView.findViewById(R.id.textview_mounth)

        fun bindHeader(transaction: TransactionDateItem) {
            day.text = transaction.day
            month.text = transaction.month
        }
    }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardTransactionStatus: ImageView =
            itemView.findViewById(R.id.textview_status)
        private val cardTime: TextView =
            itemView.findViewById(R.id.textview_time)
        private val cardTransactionTypeDescription: TextView =
            itemView.findViewById(R.id.textview_typedescription)
        private val cardTransactionType: TextView =
            itemView.findViewById(R.id.textview_type)
        private val cardTransactionValue: TextView =
            itemView.findViewById(R.id.textview_value)

        fun bindList(transaction: TransactionItem) {
            cardTransactionValue.text = transaction.transaction.value
            cardTransactionType.text = transaction.transaction.type
            cardTransactionTypeDescription.text = transaction.transaction.typeDescription
            cardTime.text = transaction.transaction.time
            if (transaction.transaction.status == "canceled"){
                cardTransactionStatus.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, R.drawable.ic_baseline_close_24))
                cardTransactionStatus.setColorFilter(
                    ContextCompat.getColor(itemView.context, R.color.grayExpense))
                cardTransactionType.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.grayExpense))
                cardTransactionValue.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.grayExpense))
            } else if ((transaction.transaction.type == "Pagamento") && (transaction.transaction.status != "canceled")){
                cardTransactionStatus.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, R.drawable.ic_baseline_south_east_24))
                cardTransactionStatus.setColorFilter(
                    ContextCompat.getColor(itemView.context, R.color.greenExpense))
                cardTransactionType.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.greenExpense))
                cardTransactionValue.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.greenExpense))
            } else {
                cardTransactionStatus.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, R.drawable.ic_baseline_north_east_24))
                cardTransactionStatus.setColorFilter(
                    ContextCompat.getColor(itemView.context, R.color.redExpense))
                cardTransactionType.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.redExpense))
                cardTransactionValue.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.redExpense))
            }
        }
    }
}
