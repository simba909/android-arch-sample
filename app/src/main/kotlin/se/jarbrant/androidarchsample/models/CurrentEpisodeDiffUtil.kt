package se.jarbrant.androidarchsample.models

import android.support.v7.util.DiffUtil
import se.jarbrant.androidarchsample.models.CurrentEpisode

/**
 * @author Simon Jarbrant
 * Created on 2017-11-07.
 */
class CurrentEpisodeDiffUtil(
        private val oldList: List<CurrentEpisode>,
        private val newList: List<CurrentEpisode>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEpisode = oldList[oldItemPosition]
        val newEpisode = newList[newItemPosition]

        return oldEpisode.id == newEpisode.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEpisode = oldList[oldItemPosition]
        val newEpisode = newList[newItemPosition]

        return oldEpisode == newEpisode
    }
}
