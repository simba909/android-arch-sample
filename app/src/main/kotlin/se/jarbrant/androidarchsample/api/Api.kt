package se.jarbrant.androidarchsample.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.jarbrant.androidarchsample.api.deserialize.ChannelDeserializer
import se.jarbrant.androidarchsample.api.deserialize.CurrentEpisodeDeserializer
import se.jarbrant.androidarchsample.api.deserialize.CurrentEpisodesDeserializer
import se.jarbrant.androidarchsample.data.Channel
import se.jarbrant.androidarchsample.data.CurrentEpisode
import se.jarbrant.androidarchsample.api.response.CurrentEpisodesResponse

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
object Api {

    val client: SRApi
    const val baseUrl = "https://api.sr.se/api/v2/"

    private val gson: Gson = GsonBuilder()
            .registerTypeAdapter(Channel::class.java, ChannelDeserializer())
            .registerTypeAdapter(CurrentEpisode::class.java, CurrentEpisodeDeserializer())
            .registerTypeAdapter(CurrentEpisodesResponse::class.java, CurrentEpisodesDeserializer())
            .create()

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        client = retrofit.create(SRApi::class.java)
    }
}
