package se.jarbrant.androidarchsample.api

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import se.jarbrant.androidarchsample.api.response.ChannelsResponse
import se.jarbrant.androidarchsample.api.response.CurrentEpisodesResponse
import se.jarbrant.androidarchsample.api.response.EpisodesResponse
import se.jarbrant.androidarchsample.data.CurrentEpisode

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
interface SRApi {

    companion object {
        const val baseUrl = "https://api.sr.se/api/v2/"
    }

    @GET("channels?format=json&pagination=false")
    fun getChannels(): Call<ChannelsResponse>

    @GET("scheduledepisodes/rightnow?format=json&pagination=false")
    fun getPlayingEpisodes(): Call<CurrentEpisodesResponse>

    @GET("scheduledepisodes/rightnow?format=json&pagination=false")
    fun getPlayingEpisode(@Query("channelid") channelId: Int): Call<CurrentEpisode>

    @GET("episodes/getmostlistened?format=json&ondemandaudiotemplateid=9")
    fun getPopularEpisodes(@Query("size") size: Int): Single<EpisodesResponse>
}
