package alura.com.gringotts.view.filter.adapter

import alura.com.gringotts.R
import alura.com.gringotts.presentation.filter.model.Filter
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
        fun callbackPosition(position: Int)
    }

    private var lastPositionSelected: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.filter_item, parent, false)
        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        if (lastPositionSelected == -1 && filter[position].isChecked)
            lastPositionSelected = position
        holder.bind(filter[position])
        holder.itemView.setOnClickListener {
            if (lastPositionSelected != position) {
                filter[lastPositionSelected].isChecked = false
                filter[position].isChecked = true
                lastPositionSelected = position
                notifyDataSetChanged()
            }
            selectItemFilterListener.callbackPosition(lastPositionSelected)
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
            if (filter.isChecked) {
                cardText.setCheckMarkDrawable(R.drawable.ic_baseline_check_24)
            } else {
                cardText.setCheckMarkDrawable(R.drawable.golden_rounded_corner)
            }
        }
    }
}
