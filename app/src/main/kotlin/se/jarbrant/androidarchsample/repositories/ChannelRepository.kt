package se.jarbrant.androidarchsample.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import se.jarbrant.androidarchsample.networking.Api
import se.jarbrant.androidarchsample.networking.response.ChannelsResponse
import se.jarbrant.androidarchsample.models.Channel
import se.jarbrant.androidarchsample.models.database.DatabaseManager

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
object ChannelRepository {

    private val TAG: String = ChannelRepository::class.java.simpleName

    private val channelDao = DatabaseManager.database.channelDao()

    fun getChannel(channelId: Int): Channel {
        return channelDao.load(channelId)
    }

    fun getChannels(): LiveData<List<Channel>> {
        refreshChannels()
        return channelDao.loadAll()
    }

    fun getNationalChannels(): LiveData<List<Channel>> {
        refreshChannels()
        return channelDao.loadByType(Channel.TYPE_NATIONAL)
    }

    fun refreshChannels(completionBody: ((List<Channel>) -> Unit)? = null) {
        Log.d(TAG, "Refreshing channels...")

        Api.client.getChannels().enqueue(object : Callback<ChannelsResponse> {

            override fun onFailure(call: Call<ChannelsResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch channels", t)
                // TODO -> Forward this to the view
            }

            override fun onResponse(call: Call<ChannelsResponse>,
                                    response: Response<ChannelsResponse>) {

                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    val channels = responseBody.channels
                    AsyncTask.execute {
                        Log.d(TAG, "Saving channel data... -> $channels")
                        channelDao.save(*channels.toTypedArray())

                        if (completionBody != null) {
                            completionBody(channels)
                        }
                    }

                } else {
                    Log.w(TAG, "The call to get channels was not successful, or the" +
                            " response was empty :(")
                }
            }
        })
    }
}
