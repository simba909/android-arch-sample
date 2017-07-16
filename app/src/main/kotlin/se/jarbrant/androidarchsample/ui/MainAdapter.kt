package se.jarbrant.androidarchsample.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import se.jarbrant.androidarchsample.R
import se.jarbrant.androidarchsample.data.Channel

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
class MainAdapter : RecyclerView.Adapter<ChannelCardHolder>() {

    private var items: List<Channel> = emptyList()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ChannelCardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_channel, parent, false)

        return ChannelCardHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelCardHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(newData: List<Channel>) {
        items = newData

        // TODO -> This should use DiffUtil instead
        notifyDataSetChanged()
    }
}
