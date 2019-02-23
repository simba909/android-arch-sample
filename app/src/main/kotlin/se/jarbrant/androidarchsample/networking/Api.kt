package se.jarbrant.androidarchsample.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import se.jarbrant.androidarchsample.networking.deserialize.ChannelDeserializer
import se.jarbrant.androidarchsample.networking.deserialize.CurrentEpisodeDeserializer
import se.jarbrant.androidarchsample.networking.deserialize.CurrentEpisodesDeserializer
import se.jarbrant.androidarchsample.models.Channel
import se.jarbrant.androidarchsample.models.CurrentEpisode
import se.jarbrant.androidarchsample.networking.response.CurrentEpisodesResponse

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
object Api {

    val client: SRApi

    private val gson: Gson = GsonBuilder()
            .registerTypeAdapter(Channel::class.java, ChannelDeserializer())
            .registerTypeAdapter(CurrentEpisode::class.java, CurrentEpisodeDeserializer())
            .registerTypeAdapter(CurrentEpisodesResponse::class.java, CurrentEpisodesDeserializer())
            .create()

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(SRApi.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        client = retrofit.create(SRApi::class.java)
    }
}
