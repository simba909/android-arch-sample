package se.jarbrant.androidarchsample.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import se.jarbrant.androidarchsample.R
import se.jarbrant.androidarchsample.data.Channel

/**
 * @author Simon Jarbrant
 * Created on 2017-07-16.
 */
class ChannelCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image: ImageView = itemView.findViewById(R.id.card_image)
    private val superTitle: TextView = itemView.findViewById(R.id.card_super_title)
    private val title: TextView = itemView.findViewById(R.id.card_title)

    fun bind(item: Channel) {
        item.image?.let { imagePath ->
            Picasso.with(itemView.context)
                    .load(imagePath)
                    .into(image)
        }

        superTitle.text = item.name
        superTitle.setTextColor(item.color)
    }
}
