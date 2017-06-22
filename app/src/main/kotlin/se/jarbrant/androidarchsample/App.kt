package se.jarbrant.androidarchsample

import android.app.Application
import android.util.Log
import se.jarbrant.androidarchsample.repositories.DatabaseRepository

/**
 * @author Simon Jarbrant
 * Created on 2017-06-21.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "Initializing database...")
        DatabaseRepository.initialize(applicationContext)
    }

    companion object {
        private val TAG: String = App::class.java.simpleName
    }
}
