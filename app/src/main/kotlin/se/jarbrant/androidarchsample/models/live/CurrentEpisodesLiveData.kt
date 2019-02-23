package se.jarbrant.androidarchsample.models.live

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.Handler
import android.util.Log
import se.jarbrant.androidarchsample.extensions.TAG
import se.jarbrant.androidarchsample.extensions.postDelayed
import se.jarbrant.androidarchsample.models.Channel
import se.jarbrant.androidarchsample.models.CurrentEpisode
import se.jarbrant.androidarchsample.networking.Api
import se.jarbrant.androidarchsample.networking.communicator.Communicator
import se.jarbrant.androidarchsample.networking.communicator.CommunicatorTask

/**
 * @author Simon Jarbrant
 * Created on 2017-07-16.
 */
class CurrentEpisodesLiveData(
        channelSource: LiveData<List<Channel>>,
        private val communicator: Communicator
) : MediatorLiveData<List<CurrentEpisode>>() {

    private val handler = Handler()
    private var channelIds: IntArray = intArrayOf()
    private var refreshTask: CommunicatorTask? = null

    init {
        addSource(channelSource) { channels ->
            if (channels != null) {
                // We have new channels!
                Log.d(TAG, "New channels: $channels")
                channelIds = channels.map { it.id }.toIntArray()
                refresh()
            }
        }
    }

    override fun onActive() {
        super.onActive()

        if (channelIds.isNotEmpty()) {
            refresh()
        }
    }

    override fun onInactive() {
        super.onInactive()

        // Cancel any future refreshes
        handler.removeCallbacksAndMessages(null)

        // Cancel any ongoing refresh tasks
        refreshTask?.cancel()
    }

    private fun refresh() {
        Log.d(TAG, "Refreshing currently playing episodes...")
        val call = Api.client.getPlayingEpisodes()
        refreshTask = communicator.perform(call) { result ->
            val data = result.getOrNull()

            if (data == null) {
                Log.e(TAG, "Couldn't fetch currently playing episodes", result.exceptionOrNull())
                return@perform
            }

            val episodes = data.episodes.filter { episode ->
                val channelId = episode.channel?.id
                channelId != null && channelId in channelIds
            }

            scheduleNextUpdate(episodes)
            value = episodes
        }
    }

    private fun scheduleNextUpdate(episodes: List<CurrentEpisode>) {
        val soonestEndingEpisode = episodes.minBy { it.endTime } ?: return
        var timeLeft = soonestEndingEpisode.endTime - System.currentTimeMillis()

        if (timeLeft < 3000L) {
            Log.d(TAG, "Next update is too soon, postponing 3 seconds")
            timeLeft = 3000L
        }

        Log.d(TAG, "Scheduling next update in $timeLeft ms")

        handler.removeCallbacksAndMessages(null)
        handler.postDelayed(timeLeft) {
            refresh()
        }
    }
}
