package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.data.model.Benefits
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BenefitsListAdapter(val benefitsList: List<Benefits>) : RecyclerView.Adapter<BenefitsListAdapter.BenefitsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenefitsViewHolder {
        //BenefitsListAdapter
    }

    override fun onBindViewHolder(holder: BenefitsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class BenefitsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val cardColor: View = itemView.findViewById(R.id.card_color)
        private val cardTitle: TextView = itemView.findViewById(R.id.benefits_card_title)
        private val cardMessage: TextView = itemView.findViewById(R.id.benefits_card_message)
        private val cardLink: TextView = itemView.findViewById(R.id.benefits_card_link)
        private val cardImage: ImageView = itemView.findViewById(R.id.benefits_card_image)
        fun bind(benefits: Benefits) {
            cardTitle.text = benefits.title
            cardMessage.text = benefits.message
            cardLink.text = benefits.textLink
        }
    }
}