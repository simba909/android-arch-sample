package se.jarbrant.androidarchsample.utils.extensions

import android.os.Handler

/**
 * @author Simon Jarbrant
 * Created on 2017-11-07.
 */
fun Handler.postDelayed(delayMillis: Long, body: () -> Unit) {
    postDelayed(body, delayMillis)
}
