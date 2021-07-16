package alura.com.gringotts.view.home.adapters

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.Filter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView


class FilterListAdapter(
    private val filter: List<Filter>,
    private val selectItemFilterListener: SelectItemFilterListener
) :
    RecyclerView.Adapter<FilterListAdapter.FilterViewHolder>() {

    interface SelectItemFilterListener {
        fun returnPosition(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.filter_item, parent, false)
        return FilterViewHolder(view)
    }

    var positionSelect = 1
    var wasClicked = false
    var lastPosition = -1

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(filter[position])
        holder.itemView.setOnClickListener {
            positionSelect = position

            val value: Boolean = holder.cardText.isChecked
            if (value) {
                // set check mark drawable and set checked property to false
                holder.cardText.setCheckMarkDrawable(R.drawable.golden_rounded_corner)//por um icone que n aparece nada, tipo esse
                holder.cardText.isChecked = false
            } else {
                // set check mark drawable and set checked property to true
                holder.cardText.setCheckMarkDrawable(R.drawable.ic_adicionar_dinheiro)
                holder.cardText.isChecked = true
            }

            selectItemFilterListener.returnPosition(positionSelect)

        }
    }

    override fun getItemCount(): Int {
        return filter.size
    }

    class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardText: CheckedTextView =
            itemView.findViewById(R.id.filters_filter) as CheckedTextView
        fun bind(filter: Filter) {
            cardText.text = filter.text
        }
    }
}
