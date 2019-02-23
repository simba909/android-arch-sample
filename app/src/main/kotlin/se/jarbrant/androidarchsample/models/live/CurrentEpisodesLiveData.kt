package se.jarbrant.androidarchsample.models.live

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.Handler
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import se.jarbrant.androidarchsample.networking.Api
import se.jarbrant.androidarchsample.networking.response.CurrentEpisodesResponse
import se.jarbrant.androidarchsample.models.Channel
import se.jarbrant.androidarchsample.models.CurrentEpisode
import se.jarbrant.androidarchsample.utils.extensions.postDelayed

/**
 * @author Simon Jarbrant
 * Created on 2017-07-16.
 */
class CurrentEpisodesLiveData(channelSource: LiveData<List<Channel>>)
    : MediatorLiveData<List<CurrentEpisode>>() {

    private val handler = Handler()

    private var channelIds: List<Int>? = null

    init {
        addSource(channelSource) { channels ->
            if (channels != null) {
                // We have new channels!
                Log.d(TAG, "New channels: $channels")
                channelIds = channels.map { it.id }
                refresh()
            }
        }
    }

    override fun onActive() {
        super.onActive()

        if (channelIds != null) {
            refresh()
        }
    }

    override fun onInactive() {
        super.onInactive()

        // Cancel any future refreshes
        handler.removeCallbacksAndMessages(null)
    }

    private fun refresh() {
        Log.d(TAG, "Refreshing currently playing episodes...")
        Api.client.getPlayingEpisodes().enqueue(object : Callback<CurrentEpisodesResponse> {

            override fun onFailure(call: Call<CurrentEpisodesResponse>, t: Throwable) {
                Log.e(TAG, "Couldn't fetch currently playing episodes", t)
            }

            override fun onResponse(call: Call<CurrentEpisodesResponse>,
                                    response: Response<CurrentEpisodesResponse>) {

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        channelIds?.let { ids ->
                            val episodes = responseBody.episodes.filter {
                                it.channel?.id in ids
                            }

                            value = episodes
                            scheduleNextUpdate(episodes)
                        }
                    }
                } else {
                    // meh!
                }
            }
        })
    }

    private fun scheduleNextUpdate(episodes: List<CurrentEpisode>) {
        // Post a new runnable to refresh again
        var nextUpdate = Long.MAX_VALUE

        episodes.forEach {
            if (it.endTime < nextUpdate) {
                nextUpdate = it.endTime
            }
        }

        var timeLeft = nextUpdate - System.currentTimeMillis()

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

    companion object {
        private val TAG: String = CurrentEpisodesLiveData::class.java.simpleName
    }
}
