package se.jarbrant.androidarchsample.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
@Database(entities = arrayOf(Channel::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun channelDao(): ChannelDao

    companion object {
        const val DATABASE_NAME = "arch-sample-sr-db"
    }
}
