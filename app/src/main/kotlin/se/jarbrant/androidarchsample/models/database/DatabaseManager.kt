package se.jarbrant.androidarchsample.models.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context
import android.util.Log
import se.jarbrant.androidarchsample.models.Channel

/**
 * @author Simon Jarbrant
 * Created on 2017-06-21.
 */
object DatabaseManager {

    private val TAG: String = DatabaseManager::class.java.simpleName

    private var initialized = false

    lateinit var database: AppDatabase
        private set

    fun initialize(context: Context) {
        if (initialized) {
            return
        }

        database = Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
                .addMigrations(MIGRATION1_2)
                .build()

        initialized = true
    }

    // Migrations
    private val MIGRATION1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            Log.d(TAG, "Migrating from version 1 to version 2...")
            database.execSQL("ALTER TABLE ${Channel.TABLE_NAME} " +
                    "ADD COLUMN ${Channel.FIELD_IMAGE} TEXT")
        }
    }
}
