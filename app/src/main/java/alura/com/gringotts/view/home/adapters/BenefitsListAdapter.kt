package alura.com.gringotts.view.home.adapters

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.Benefit
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BenefitsListAdapter(private val benefits: List<Benefit>) :
    RecyclerView.Adapter<BenefitsListAdapter.BenefitsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenefitsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.benefits_item, parent, false)
        return BenefitsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BenefitsViewHolder, position: Int) {
        holder.bind(benefits[position])
    }

    override fun getItemCount(): Int {
        return benefits.size
    }

    class BenefitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardColor: View = itemView.findViewById(R.id.card_color)
        private val cardTitle: TextView = itemView.findViewById(R.id.benefits_card_title)
        private val cardMessage: TextView = itemView.findViewById(R.id.benefits_card_message)
        private val cardLink: TextView = itemView.findViewById(R.id.benefits_card_link)
        private val cardImage: ImageView = itemView.findViewById(R.id.benefits_card_image)
        fun bind(benefit: Benefit) {
            cardTitle.text = benefit.title
            cardMessage.text = benefit.message
            cardLink.text = benefit.textLink
            cardColor.setBackgroundColor(Color.parseColor(benefit.indicatorColor))
            Picasso.get().load(benefit.image).into(cardImage)
        }
    }
}
