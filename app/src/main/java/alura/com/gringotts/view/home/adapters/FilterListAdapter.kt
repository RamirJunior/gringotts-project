package alura.com.gringotts.view.home.adapters

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.Filter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilterListAdapter(private val filter: List<Filter>) :
    RecyclerView.Adapter<FilterListAdapter.FilterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.filter_item, parent, false)
        return FilterViewHolder(view)
    }

    companion object {
        private const val POSITION = "position"
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(filter[position])
    }

    override fun getItemCount(): Int {
        return filter.size
    }

    class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardText: TextView = itemView.findViewById(R.id.filters_filter)
        fun bind(filter: Filter) {
            cardText.text = filter.text
        }
    }
}
