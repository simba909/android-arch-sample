package se.jarbrant.androidarchsample.networking.deserialize

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import se.jarbrant.androidarchsample.models.CurrentEpisode
import se.jarbrant.androidarchsample.models.database.DatabaseManager
import se.jarbrant.androidarchsample.networking.response.CurrentEpisodesResponse
import java.lang.reflect.Type

/**
 * @author Simon Jarbrant
 * Created on 2017-07-17.
 */
class CurrentEpisodesDeserializer : JsonDeserializer<CurrentEpisodesResponse> {

    private val channelDao = DatabaseManager.database.channelDao()

    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext): CurrentEpisodesResponse {

        val channelsJson = json.asJsonObject.getAsJsonArray("channels")

        val episodes = mutableListOf<CurrentEpisode>()

        for (element in channelsJson) {
            val channelJson = element.asJsonObject

            if (channelJson.has("currentscheduledepisode")) {
                val episodeJson = channelJson.get("currentscheduledepisode").asJsonObject
                val episode = CurrentEpisode.from(episodeJson)

                if (channelJson.has("id")) {
                    val channelId = channelJson.get("id").asInt
                    episode.channel = channelDao.load(channelId)
                }

                episodes.add(episode)
            }
        }

        return CurrentEpisodesResponse(episodes)
    }
}
