package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.data.model.Benefits
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableContainer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BenefitsListAdapter(private val benefitsList: List<Benefits>)
    : RecyclerView.Adapter<BenefitsListAdapter.BenefitsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenefitsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.benefits_item, parent, false)
        return BenefitsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BenefitsViewHolder, position: Int) {
        holder.bind(benefitsList[position])
    }

    override fun getItemCount(): Int {
        return benefitsList.size
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
            cardColor.setBackgroundColor(Color.parseColor(benefits.indicatorColor))
            Picasso.get().load(benefits.image).into(cardImage)
        }
    }
}