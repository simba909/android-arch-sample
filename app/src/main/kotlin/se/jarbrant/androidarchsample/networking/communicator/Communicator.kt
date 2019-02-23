package se.jarbrant.androidarchsample.networking.communicator

import retrofit2.Call

interface Communicator {
    fun <T, C : Call<T>> perform(call: C, completionHandler: (Result<T>) -> Unit): CommunicatorTask
}
