package se.jarbrant.androidarchsample.api.deserialize

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import se.jarbrant.androidarchsample.data.CurrentEpisode
import se.jarbrant.androidarchsample.api.response.CurrentEpisodesResponse
import se.jarbrant.androidarchsample.repositories.ChannelRepository
import java.lang.reflect.Type

/**
 * @author Simon Jarbrant
 * Created on 2017-07-17.
 */
class CurrentEpisodesDeserializer : JsonDeserializer<CurrentEpisodesResponse> {

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
                    episode.channel = ChannelRepository.getChannel(channelId)
                }

                episodes.add(episode)
            }
        }

        return CurrentEpisodesResponse(episodes)
    }
}
