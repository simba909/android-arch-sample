package se.jarbrant.androidarchsample.api.deserialize

import android.graphics.Color
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import se.jarbrant.androidarchsample.data.Channel
import se.jarbrant.androidarchsample.extensions.getOrElse
import java.lang.reflect.Type

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
class ChannelDeserializer : JsonDeserializer<Channel> {

    override fun deserialize(json: JsonElement,
                             type: Type,
                             context: JsonDeserializationContext?): Channel {

        val jsonRoot = json as JsonObject

        val id = jsonRoot.getOrElse("id", -1)
        val name = resolveName(id, jsonRoot.getOrElse("name", "[No channel]"))
        val channelType = resolveType(id, jsonRoot.getOrElse("channeltype", ""))
        val channelColor = resolveColor(id, jsonRoot.getOrElse("color", "404242"))

        return Channel(id, name, channelType, channelColor)
    }

    private fun resolveName(channelId: Int, fallbackName: String): String {
        return when (channelId) {
            P2_LANG_AND_MUSIC_ID -> P2_SPECIAL_NAME_NATIONAL
            P2_MUSIC_ID -> P2_SPECIAL_NAME_LANG_AND_MUSIC
            else -> fallbackName
        }
    }

    private fun resolveType(id: Int, type: String): Int {
        val filteredType = when (id) {
            SISU_ID -> "rikskanal"
            P2_LANG_AND_MUSIC_ID -> "rikskanal"
            P2_MUSIC_ID -> "minoritet och språk"
            else -> type
        }

        return when (filteredType.toLowerCase()) {
            "rikskanal" -> Channel.TYPE_NATIONAL
            "lokal kanal" -> Channel.TYPE_LOCAL
            "minoritet och språk" -> Channel.TYPE_MINORITY
            "fler kanaler" -> Channel.TYPE_WEB
            "extrakanaler" -> Channel.TYPE_EXTRA
            else -> Channel.TYPE_NONE
        }
    }

    private fun resolveColor(id: Int, rgb: String): Int {
        return when (id) {
            SISU_ID -> Color.parseColor("#0068C1")
            P5_ID -> Color.parseColor("#E3004A")
            else -> Color.parseColor("#" + rgb)
        }
    }

    companion object {
        const val P2_LANG_AND_MUSIC_ID = 2562
        const val P2_MUSIC_ID = 163
        const val P5_ID = 2842
        const val SISU_ID = 226
        const val P2_SPECIAL_NAME_NATIONAL = "P2"
        const val P2_SPECIAL_NAME_LANG_AND_MUSIC = "P2 Språk och musik"
    }
}
