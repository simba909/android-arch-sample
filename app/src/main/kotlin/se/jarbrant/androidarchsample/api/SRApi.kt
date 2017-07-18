package se.jarbrant.androidarchsample.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import se.jarbrant.androidarchsample.data.Channel
import se.jarbrant.androidarchsample.data.ChannelHolder
import se.jarbrant.androidarchsample.data.CurrentEpisode
import se.jarbrant.androidarchsample.data.CurrentEpisodesResponse

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
interface SRApi {

    @GET("channels?format=json&pagination=false")
    fun getChannels(): Call<ChannelHolder>

    @GET("scheduledepisodes/rightnow?format=json&pagination=false")
    fun getPlayingEpisodes(): Call<CurrentEpisodesResponse>

    @GET("scheduledepisodes/rightnow?format=json&pagination=false")
    fun getPlayingEpisode(@Query("channelid") channelId: Int): Call<CurrentEpisode>
}
