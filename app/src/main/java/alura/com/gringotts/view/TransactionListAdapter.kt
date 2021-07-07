package alura.com.gringotts.view;

import alura.com.gringotts.R
import alura.com.gringotts.data.model.Transaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.List

class TransactionListAdapter(private val transaction: List<Transaction>) :
    RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transaction[position])
    }

    override fun getItemCount(): Int {
        return transaction.size
    }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //private val cardColor: View = itemView.findViewById(R.id.card_color)
        //private val cardTitle: TextView = itemView.findViewById(R.id.benefits_card_title)
        //private val cardMessage: TextView = itemView.findViewById(R.id.benefits_card_message)
        //private val cardLink: TextView = itemView.findViewById(R.id.benefits_card_link)
        //private val cardImage: ImageView = itemView.findViewById(R.id.benefits_card_image)
        //private val cardImage: ImageView = itemView.findViewById(R.id.benefits_card_image)
        fun bind(transaction: Transaction) {
            //cardTitle.text = benefit.title
            //cardMessage.text = benefit.message
            //cardLink.text = benefit.textLink
            //cardColor.setBackgroundColor(Color.parseColor(benefit.indicatorColor))
            //cardLink.text = benefit.textLink
            //Picasso.get().load(benefit.image).into(cardImage)
        }
    }
}
