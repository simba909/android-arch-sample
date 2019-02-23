package se.jarbrant.androidarchsample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import se.jarbrant.androidarchsample.models.Channel
import se.jarbrant.androidarchsample.models.live.CurrentEpisodesLiveData
import se.jarbrant.androidarchsample.repositories.ChannelRepository

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
class ChannelViewModel : ViewModel() {

    private val repository by lazy { ChannelRepository }

    val channels: LiveData<List<Channel>>
        get() = repository.getChannels()

    val nationalChannels: LiveData<List<Channel>>
        get() = repository.getNationalChannels()

    val currentEpisodes by lazy { CurrentEpisodesLiveData(nationalChannels) }
}
