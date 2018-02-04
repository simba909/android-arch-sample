package se.jarbrant.androidarchsample.ui.lists

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import se.jarbrant.androidarchsample.R
import se.jarbrant.androidarchsample.data.Episode

/**
 * @author Simon Jarbrant
 * Created on 2018-02-03.
 */
class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder>() {

    var items: List<Episode> = emptyList()
        set(value) {
            field = value
            // TODO -> Use DiffUtil
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_episode, parent, false)

        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.list_item_episode_image)
        private val titleView: TextView = itemView.findViewById(R.id.list_item_episode_title)

        fun bind(episode: Episode) {
            titleView.text = episode.title

            Picasso.with(itemView.context)
                    .load(episode.imageUrl)
                    .fit()
                    .centerCrop()
                    .into(imageView)
        }

        companion object {
            private val TAG: String = EpisodeViewHolder::class.java.simpleName
        }
    }
}
