package se.jarbrant.androidarchsample.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import se.jarbrant.androidarchsample.R
import se.jarbrant.androidarchsample.data.CurrentEpisode
import se.jarbrant.androidarchsample.extensions.toShortClock

/**
 * @author Simon Jarbrant
 * Created on 2017-07-16.
 */
class CurrentEpisodeHolder(itemView: View) : BindableViewHolder<CurrentEpisode>(itemView) {

    private val image: ImageView = itemView.findViewById(R.id.card_image)
    private val superTitle: TextView = itemView.findViewById(R.id.card_super_title)
    private val title: TextView = itemView.findViewById(R.id.card_title)
    private val progress: ProgressBar = itemView.findViewById(R.id.card_progress)
    private val startTime: TextView = itemView.findViewById(R.id.card_start_time)
    private val endTime: TextView = itemView.findViewById(R.id.card_end_time)

    override fun bind(item: CurrentEpisode) {
        item.image?.let { imagePath ->
            Picasso.with(itemView.context)
                    .load(imagePath)
                    .into(image)
        }

        item.channel?.let {
            superTitle.text = it.name
            superTitle.setTextColor(it.color)
        }

        title.text = item.title

        startTime.text = item.startTime.toShortClock()
        endTime.text = item.endTime.toShortClock()

        setProgress(item)
    }

    private fun setProgress(episode: CurrentEpisode) {
        if (episode.startTime == 0L || episode.endTime == 0L) return

        val currentTime = System.currentTimeMillis()

        val elapsedTime = currentTime - episode.startTime
        val percent = elapsedTime / episode.duration.toDouble()

        progress.progress = (100 * percent).toInt()
    }
}
