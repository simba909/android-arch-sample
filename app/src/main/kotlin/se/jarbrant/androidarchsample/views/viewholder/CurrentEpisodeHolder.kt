package se.jarbrant.androidarchsample.views.viewholder

import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import se.jarbrant.androidarchsample.R
import se.jarbrant.androidarchsample.models.CurrentEpisode
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

    private val progressUpdateHandler = Handler()
    private var episode: CurrentEpisode? = null

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

        episode = item
        setProgress()
    }

    override fun unbind() {
        progressUpdateHandler.removeCallbacksAndMessages(null)
        episode = null
    }

    private fun setProgress() {
        episode?.let {
            if (it.startTime == 0L || it.endTime == 0L) return

            val elapsedTime = System.currentTimeMillis() - it.startTime
            val percent = elapsedTime / it.duration.toDouble()

            progress.progress = (100 * percent).toInt()

            progressUpdateHandler.postDelayed({
                setProgress()
            }, 3000L)
        }
    }
}
