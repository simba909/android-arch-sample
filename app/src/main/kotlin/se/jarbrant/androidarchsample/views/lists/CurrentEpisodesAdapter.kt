package se.jarbrant.androidarchsample.views.lists

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import se.jarbrant.androidarchsample.R
import se.jarbrant.androidarchsample.models.CurrentEpisode
import se.jarbrant.androidarchsample.views.viewholder.CurrentEpisodeHolder
import se.jarbrant.androidarchsample.models.CurrentEpisodeDiffUtil

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
class CurrentEpisodesAdapter : RecyclerView.Adapter<CurrentEpisodeHolder>() {

    var items: List<CurrentEpisode> = emptyList()
        set(value) {
            val differ = CurrentEpisodeDiffUtil(field, value)
            val result = DiffUtil.calculateDiff(differ)

            field = value

            result.dispatchUpdatesTo(this)
        }

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
}
