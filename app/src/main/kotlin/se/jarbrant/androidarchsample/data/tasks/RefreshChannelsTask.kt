package se.jarbrant.androidarchsample.data.tasks

import android.os.AsyncTask
import android.util.Log
import se.jarbrant.androidarchsample.api.Api
import se.jarbrant.androidarchsample.data.Channel
import se.jarbrant.androidarchsample.data.ChannelDao

/**
 * @author Simon Jarbrant
 * Created on 2017-07-18.
 */
class RefreshChannelsTask(val channelDao: ChannelDao) : AsyncTask<Void, Void, List<Channel>?>() {

    override fun doInBackground(vararg p0: Void?): List<Channel>? {
        // First of all, we only need to load channels if we don't have any

        val response = Api.client.getChannels().execute()

        if (response.isSuccessful && response.body() != null) {
            val body = response.body()

            if (body != null) {
                return body.channels
            } else {
                // TODO -> Handle!
            }
        } else {
            // TODO -> Handle!
        }

        return null
    }

    override fun onPostExecute(result: List<Channel>?) {
        if (result != null) {
            Log.d(TAG, "Saving channel data... -> $result")
            channelDao.save(*result.toTypedArray())
        }
    }

    companion object {
        private val TAG: String = RefreshChannelsTask::class.java.simpleName
    }
}
