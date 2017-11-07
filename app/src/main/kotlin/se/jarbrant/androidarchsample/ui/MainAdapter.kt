package se.jarbrant.androidarchsample.ui

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import se.jarbrant.androidarchsample.R
import se.jarbrant.androidarchsample.data.CurrentEpisode
import se.jarbrant.androidarchsample.ui.viewholder.CurrentEpisodeHolder
import se.jarbrant.androidarchsample.utils.CurrentEpisodeDiffUtil

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
class MainAdapter : RecyclerView.Adapter<CurrentEpisodeHolder>() {

    private var items: List<CurrentEpisode> = emptyList()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): CurrentEpisodeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_current_episode, parent, false)

        return CurrentEpisodeHolder(view)
    }

    override fun onBindViewHolder(holder: CurrentEpisodeHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onViewRecycled(holder: CurrentEpisodeHolder) {
        holder.unbind()
    }

    fun setData(newData: List<CurrentEpisode>) {
        val differ = CurrentEpisodeDiffUtil(items, newData)
        val result = DiffUtil.calculateDiff(differ)

        items = newData

        result.dispatchUpdatesTo(this)
    }
}
