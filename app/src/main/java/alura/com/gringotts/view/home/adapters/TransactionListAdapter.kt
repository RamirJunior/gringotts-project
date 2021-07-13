package alura.com.gringotts.view.home.adapters

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.TransactionDateItem
import alura.com.gringotts.data.models.home.TransactionItem
import alura.com.gringotts.data.models.home.TransactionListItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransactionListAdapter(private val transaction: List<TransactionListItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return transaction[position].getItemType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TransactionListItem.TRANSACTION_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.date_item, parent, false)
            return DateViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_item, parent, false)
            return TransactionViewHolder(view)
        }
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
        val cardTransactionStatus: TextView =
            itemView.findViewById(R.id.tv_date)

        fun bindHeader(transaction: TransactionDateItem) {
            cardTransactionStatus.text = transaction.toString()
        }
    }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTransactionStatus: ImageView =
            itemView.findViewById(R.id.transaction_status)
        val cardTransactionPayment: TextView =
            itemView.findViewById(R.id.transaction_payment)
        val cardTransactionType: TextView =
            itemView.findViewById(R.id.transaction_sale_type)
        val cardTransactionValue: TextView =
            itemView.findViewById(R.id.transaction_value)

        //private val cardTransactionIcon: ImageView = itemView.findViewById(R.id.transaction_icon)
        fun bindList(transaction: TransactionItem) {
            // preencher lista do cardview
        }
    }


}
