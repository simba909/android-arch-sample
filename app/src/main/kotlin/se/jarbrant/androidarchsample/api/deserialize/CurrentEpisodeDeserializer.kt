package se.jarbrant.androidarchsample.api.deserialize

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import se.jarbrant.androidarchsample.data.CurrentEpisode
import java.lang.reflect.Type

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
class CurrentEpisodeDeserializer : JsonDeserializer<CurrentEpisode> {

    override fun deserialize(json: JsonElement,
                             type: Type,
                             context: JsonDeserializationContext): CurrentEpisode {

        val channelJson = json.asJsonObject.getAsJsonObject("channel")
        val episodeJson = channelJson.getAsJsonObject("currentscheduledepisode")

        val id: Int
        val title: String

        if (episodeJson != null) {
            id = when {
                episodeJson.has("episodeid") -> episodeJson.get("episodeid").asInt
                episodeJson.has("id") -> episodeJson.get("id").asInt
                else -> -1
            }

            title = episodeJson.get("title").asString

        } else {
            id = -1
            title = "Empty episode"
        }

        // TODO -> Get channel

        return CurrentEpisode(id, title)
    }
}
