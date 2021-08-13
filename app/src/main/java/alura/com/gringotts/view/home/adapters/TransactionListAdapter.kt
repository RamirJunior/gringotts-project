package alura.com.gringotts.view.home.adapters

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.TransactionDateItem
import alura.com.gringotts.data.models.home.TransactionItem
import alura.com.gringotts.data.models.home.TransactionListItem
import alura.com.gringotts.presentation.auxiliar.NumberFormatHelper.formatDoubleToTwoFractionDigits
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
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

    fun setAdapterList(newList: List<TransactionListItem>) {
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
        private val date: TextView = itemView.findViewById(R.id.textview_date)

        fun bindHeader(transaction: TransactionDateItem) {
            date.text = transaction.transactionDate.date
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
        private val cardTransactionCurrency: TextView =
            itemView.findViewById(R.id.textview_currency)

        fun bindList(transaction: TransactionItem) {

            cardTransactionValue.text = formatDoubleToTwoFractionDigits(
                transaction.transaction.value.toDouble()
            )
            cardTransactionType.text = transaction.transaction.type
            cardTransactionTypeDescription.text = transaction.transaction.typeDescription
            cardTime.text = transaction.transaction.time

            if (transaction.transaction.status == "canceled") {
                setColorItemView(R.color.grayExpense)
                setImageItemView(R.drawable.ic_baseline_close_24)
            } else if ((transaction.transaction.type == "Pagamento") && (transaction.transaction.status != "canceled")) {
                setColorItemView(R.color.greenExpense)
                setImageItemView(R.drawable.ic_baseline_south_east_24)
            } else {
                setColorItemView(R.color.redExpense)
                setImageItemView(R.drawable.ic_baseline_north_east_24)
            }
        }

        private fun setColorItemView(@ColorRes color: Int) {
            cardTransactionStatus.setColorFilter(
                ContextCompat.getColor(itemView.context, color)
            )
            cardTransactionCurrency.setTextColor(
                ContextCompat.getColor(itemView.context, color)
            )
            cardTransactionType.setTextColor(
                ContextCompat.getColor(itemView.context, color)
            )
            cardTransactionValue.setTextColor(
                ContextCompat.getColor(itemView.context, color)
            )
        }

        private fun setImageItemView(@DrawableRes drawable: Int) {
            cardTransactionStatus.setImageDrawable(
                ContextCompat.getDrawable(itemView.context, drawable)
            )
        }
    }
}
