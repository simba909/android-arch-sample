package se.jarbrant.androidarchsample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import se.jarbrant.androidarchsample.models.Channel
import se.jarbrant.androidarchsample.models.database.ChannelDao
import se.jarbrant.androidarchsample.models.live.CurrentEpisodesLiveData
import se.jarbrant.androidarchsample.networking.Api
import se.jarbrant.androidarchsample.networking.communicator.Communicator
import se.jarbrant.androidarchsample.networking.communicator.CommunicatorTask

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
class ChannelViewModel(
        private val channelDao: ChannelDao,
        private val communicator: Communicator
) : ViewModel() {

    private var refreshTask: CommunicatorTask? = null

    private val nationalChannels: LiveData<List<Channel>>
        get() {
            refreshChannels()
            return channelDao.loadByType(Channel.TYPE_NATIONAL)
        }

    val channels: LiveData<List<Channel>>
        get() {
            refreshChannels()
            return channelDao.loadAll()
        }

    val currentEpisodes by lazy { CurrentEpisodesLiveData(nationalChannels, communicator) }

    fun refreshChannels() {
        val channelsCall = Api.client.getChannels()
        refreshTask = communicator.perform(channelsCall) { result ->
            val channelsResponse = result.getOrNull() ?: return@perform

            AsyncTask.execute {
                channelDao.save(*channelsResponse.channels.toTypedArray())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        refreshTask?.cancel()
    }
}
