//package alura.com.gringotts.view.home.adapters
//
//import alura.com.gringotts.R
//import alura.com.gringotts.data.models.home.Transaction
//import alura.com.gringotts.data.models.home.TransactionDateItem
//import alura.com.gringotts.data.models.home.TransactionListItem
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class TransactionListAdapter(private val transaction: List<TransactionListItem>) :
//    RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>() {
//
//    override fun getItemViewType(position: Int): Int {
//        return transaction[position].getItemType()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
//        if (viewType == TransactionListItem.TRANSACTION_HEADER){
//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.date_item, parent, false)
//            return TransactionViewHolder(view)
//        } else {
//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.transaction_item, parent, false)
//            return TransactionViewHolder(view)
//        }
//    }
//
//    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
//        val type = getItemViewType(position)
//        if (type == TransactionListItem.TRANSACTION_HEADER){
//            val header: TransactionDateItem = transaction[position] as TransactionDateItem
//            holder.bind(header)
//        }
//        holder.bind(transaction[position])
//    }
//
//    override fun getItemCount(): Int {
//        return transaction.size
//    }
//
//    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val cardTransactionStatus: ImageView =
//            itemView.findViewById(R.id.tv_date)
//    }
//
//    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val cardTransactionStatus: ImageView =
//            itemView.findViewById(R.id.transaction_status)
//        private val cardTransactionPayment: TextView =
//            itemView.findViewById(R.id.transaction_payment)
//        private val cardTransactionType: TextView =
//            itemView.findViewById(R.id.transaction_sale_type)
//        private val cardTransactionValue: TextView = itemView.findViewById(R.id.transaction_value)
//
//        //private val cardTransactionIcon: ImageView = itemView.findViewById(R.id.transaction_icon)
//        fun bind(transaction: Transaction) {
//            if (transaction.status == "completed") {
//                //cardTransactionStatus.setImageIcon(R.drawable.logotipo_black)
//            } else {
//                //cardTransactionStatus.setImageIcon(R.drawable.logotipo_black)
//            }
//            cardTransactionPayment.text = transaction.type
//            cardTransactionType.text = transaction.typeDescription
//            cardTransactionValue.text = transaction.value
//        }
//    }
//
//
//
//
//}
