package se.jarbrant.androidarchsample

import android.app.Application
import android.util.Log
import se.jarbrant.androidarchsample.models.database.DatabaseManager

/**
 * @author Simon Jarbrant
 * Created on 2017-06-21.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DatabaseManager.initialize(applicationContext)
    }
}
