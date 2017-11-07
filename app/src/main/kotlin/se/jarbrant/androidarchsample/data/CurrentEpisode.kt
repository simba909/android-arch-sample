package se.jarbrant.androidarchsample.data

import com.google.gson.JsonElement
import com.google.gson.JsonObject

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
data class CurrentEpisode(val id: Int, val title: String) {

    var startTime = 0L
    var endTime = 0L

    val duration: Long
        get() = endTime - startTime

    var image: String? = null
        private set
        get() = if (field != null) field else channel?.image

    var channel: Channel? = null

    companion object {

        fun from(json: JsonObject): CurrentEpisode {
            val id = when {
                json.has("episodeid") -> json.get("episodeid").asInt
                json.has("id") -> json.get("id").asInt
                else -> -1
            }

            val title = json.get("title").asString

            val episode = CurrentEpisode(id, title)

            if (json.has("socialimage")) {
                episode.image = json.get("socialimage").asString
            }

            if (json.has("starttimeutc")) {
                episode.startTime = parseDateFromElement(json.get("starttimeutc"))
            }

            if (json.has("endtimeutc")) {
                episode.endTime = parseDateFromElement(json.get("endtimeutc"))
            }

            return episode
        }

        private fun parseDateFromElement(element: JsonElement?, default: Long = 0L): Long {
            if (element != null) {
                val regex = Regex("(\\d+)")
                val result = regex.find(element.asString)

                return result?.value?.toLong() ?: default
            }

            return default
        }
    }
}
