package alura.com.gringotts.view.filter.adapter

import alura.com.gringotts.R
import alura.com.gringotts.data.models.filter.Filter
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

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(filter[position])
        holder.itemView.setOnClickListener {
            positionSelect = position

            selectItemFilterListener.returnPosition(positionSelect)
        }
    }

    override fun getItemCount(): Int {
        return filter.size
    }

    class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardText: CheckedTextView = itemView.findViewById(R.id.filters_filter)
        fun bind(filter: Filter) {
            cardText.text = filter.text
        }
    }
}
