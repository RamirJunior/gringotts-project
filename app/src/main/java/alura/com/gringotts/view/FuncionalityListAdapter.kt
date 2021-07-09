package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.data.model.FuncionalityItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FuncionalityListAdapter(private val list: List<FuncionalityItem>) :
    RecyclerView.Adapter<FuncionalityListAdapter.FuncionalityItemHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FuncionalityItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_services_layout_item, parent, false)
        return FuncionalityItemHolder(view)
    }

    override fun onBindViewHolder(holder: FuncionalityItemHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FuncionalityItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemTitle: TextView = itemView.findViewById(R.id.title)
        private val itemImage: ImageView = itemView.findViewById(R.id.icon)

        fun bind(item: FuncionalityItem) {
            itemTitle.text = item.title
            itemImage.setImageResource(item.icon)
        }
    }

}
