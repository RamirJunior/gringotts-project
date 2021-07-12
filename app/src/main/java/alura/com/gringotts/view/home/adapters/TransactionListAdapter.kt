package alura.com.gringotts.view.home.adapters

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.Transaction
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class TransactionListAdapter(private val transaction: List<Transaction>) :
    RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item, parent, false)
        return TransactionViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transaction[position])
    }

    override fun getItemCount(): Int {
        return transaction.size
    }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardTransactionDate: TextView = itemView.findViewById(R.id.transaction_date)
        private val cardTransactionStatus: ImageView =
            itemView.findViewById(R.id.transaction_status)
        private val cardTransactionPayment: TextView =
            itemView.findViewById(R.id.transaction_payment)
        private val cardTransactionType: TextView =
            itemView.findViewById(R.id.transaction_sale_type)
        private val cardTransactionValue: TextView = itemView.findViewById(R.id.transaction_value)

        //private val cardTransactionIcon: ImageView = itemView.findViewById(R.id.transaction_icon)
        fun bind(transaction: Transaction) {
            cardTransactionDate.text = transaction.date
            if (transaction.status == "completed") {
                //cardTransactionStatus.setImageIcon(R.drawable.logotipo_black)
            } else {
                //cardTransactionStatus.setImageIcon(R.drawable.logotipo_black)
            }
            cardTransactionPayment.text = transaction.type
            cardTransactionType.text = transaction.typeDescription
            cardTransactionValue.text = transaction.value
        }
    }
}
