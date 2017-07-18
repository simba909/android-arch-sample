package se.jarbrant.androidarchsample.extensions

import java.util.*

/**
 * @author Simon Jarbrant
 * Created on 2017-07-18.
 */

fun Long.toShortClock(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this

    val hours = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes = calendar.get(Calendar.MINUTE)

    var result = ""

    result += if (hours < 10) "0$hours:" else "$hours:"
    result += if (minutes < 10) "0$minutes" else minutes

    return result
}
