package se.jarbrant.androidarchsample.repositories

import android.arch.persistence.room.Room
import android.content.Context
import se.jarbrant.androidarchsample.data.AppDatabase

/**
 * @author Simon Jarbrant
 * Created on 2017-06-21.
 */
object DatabaseRepository {

    private var initialized = false

    lateinit var database: AppDatabase
        private set

    fun initialize(context: Context) {
        if (initialized) {
            return
        }

        database = Room.databaseBuilder(context,
                                        AppDatabase::class.java,
                                        AppDatabase.DATABASE_NAME).build()
        initialized = true
    }
}
