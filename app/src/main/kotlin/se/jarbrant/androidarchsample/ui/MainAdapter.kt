package se.jarbrant.androidarchsample.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import se.jarbrant.androidarchsample.data.Channel

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
class MainAdapter : RecyclerView.Adapter<MainAdapter.Holder>() {

    private var items: List<Channel> = emptyList()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = items.get(position)
        holder.bind(data)
    }

    fun setData(newData: List<Channel>) {
        items = newData

        // TODO -> This should use DiffUtil instead
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(item: Channel) {
            textView.text = item.name
        }
    }
}
