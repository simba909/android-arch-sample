package se.jarbrant.androidarchsample.models.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import se.jarbrant.androidarchsample.models.Channel
import se.jarbrant.androidarchsample.models.database.AppDatabase.Companion.DATABASE_VERSION

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
@Database(entities = arrayOf(Channel::class), version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun channelDao(): ChannelDao

    companion object {
        const val DATABASE_NAME = "arch-sample-sr-db"
        const val DATABASE_VERSION = 2
    }
}
