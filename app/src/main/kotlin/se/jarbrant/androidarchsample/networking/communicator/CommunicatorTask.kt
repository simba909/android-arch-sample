package se.jarbrant.androidarchsample.networking.communicator

import retrofit2.Call

class CommunicatorTask(private val call: Call<*>) {
    var isCancelled = false
        private set

    fun cancel() {
        isCancelled = true
        call.cancel()
    }
}
