package se.jarbrant.androidarchsample.viewmodels.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import se.jarbrant.androidarchsample.models.database.DatabaseManager
import se.jarbrant.androidarchsample.networking.communicator.ApiCommunicator
import se.jarbrant.androidarchsample.viewmodels.ChannelViewModel

class ViewModelFactory: ViewModelProvider.Factory {

    private val channelDao = DatabaseManager.database.channelDao()
    private val communicator = ApiCommunicator()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when(modelClass) {
            ChannelViewModel::class.java -> return ChannelViewModel(channelDao, communicator) as T
        }

        throw IllegalArgumentException("Failed to find ViewModel matching: $modelClass")
    }
}
