package se.jarbrant.androidarchsample.data

import com.google.gson.annotations.SerializedName

/**
 * @author Simon Jarbrant
 * Created on 2018-02-03.
 */
data class Episode(
        val id: Int,
        val title: String,
        @SerializedName("imageurl")
        val imageUrl: String
)
