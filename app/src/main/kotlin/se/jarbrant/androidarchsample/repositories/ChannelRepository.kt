package se.jarbrant.androidarchsample.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import se.jarbrant.androidarchsample.api.Api
import se.jarbrant.androidarchsample.data.Channel
import se.jarbrant.androidarchsample.data.ChannelHolder

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
object ChannelRepository {

    private val TAG: String = ChannelRepository::class.java.simpleName

    private val channelDao = DatabaseRepository.database.channelDao()

    fun getChannels(): LiveData<List<Channel>> {
        refreshChannels()
        return channelDao.load()
    }

    fun getNationalChannels(): LiveData<List<Channel>> {
        refreshChannels()
        return channelDao.load(Channel.TYPE_NATIONAL)
    }

    private fun refreshChannels() {
        Log.d(TAG, "Refreshing channels...")

        Api.client.getChannels().enqueue(object : Callback<ChannelHolder> {

            override fun onFailure(call: Call<ChannelHolder>, t: Throwable) {
                Log.e(TAG, "Failed to fetch channels", t)
                // TODO -> Forward this to the view
            }

            override fun onResponse(call: Call<ChannelHolder>,
                                    response: Response<ChannelHolder>) {

                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    val channels = responseBody.channels
                    AsyncTask.execute {
                        Log.d(TAG, "Saving channel data... -> $channels")
                        channelDao.save(*channels.toTypedArray())
                    }

                } else {
                    Log.w(TAG, "The call to get channels was not successful, or the" +
                            " response was empty :(")
                }
            }
        })
    }
}
