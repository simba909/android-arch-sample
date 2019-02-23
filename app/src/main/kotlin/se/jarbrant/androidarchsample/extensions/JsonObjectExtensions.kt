package se.jarbrant.androidarchsample.utils.extensions

import com.google.gson.JsonObject

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */

fun JsonObject.getOrElse(key: String, default: String): String = get(key)?.asString ?: default

fun JsonObject.getOrElse(key: String, default: Int): Int = get(key)?.asInt ?: default
