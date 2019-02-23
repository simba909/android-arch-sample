package se.jarbrant.androidarchsample.networking.communicator

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCommunicator : Communicator {
    override fun <T, C : Call<T>> perform(call: C, completionHandler: (Result<T>) -> Unit): CommunicatorTask {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                completionHandler(Result.failure(t))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (!response.isSuccessful) {
                    val httpError = Error("HTTP error ${response.code()}; ${response.message()}")
                    completionHandler(Result.failure(httpError))
                    return
                }

                val body = response.body()

                if (body == null) {
                    val bodyMissingError = Error("HTTP response body missing")
                    completionHandler(Result.failure(bodyMissingError))
                    return
                }

                completionHandler(Result.success(body))
            }
        })

        return CommunicatorTask(call)
    }
}
