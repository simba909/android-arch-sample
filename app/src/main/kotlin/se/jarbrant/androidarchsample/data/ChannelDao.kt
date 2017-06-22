package se.jarbrant.androidarchsample.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
@Dao
interface ChannelDao {

    @Query("SELECT * FROM $TABLE_NAME $ORDER_BY_NAME")
    fun load(): LiveData<List<Channel>>

    /**
     * Note: ":arg0" is because of an unfortunate bug in Kotlin < 1.1.3
     *
     * See: https://youtrack.jetbrains.com/issue/KT-17959
     *
     * TODO -> Rename this after updating to Kotlin 1.1.3 or later
     */
    @Query("SELECT * FROM $TABLE_NAME WHERE ${Channel.FIELD_ID} IN (:p0) $ORDER_BY_NAME")
    fun load(channelsIds: IntArray): LiveData<List<Channel>>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${Channel.FIELD_TYPE} = :p0 $ORDER_BY_NAME")
    fun load(type: Int): LiveData<List<Channel>>

    @Insert(onConflict = REPLACE)
    fun save(vararg channels: Channel)

    @Update(onConflict = REPLACE)
    fun update(channel: Channel)

    @Delete
    fun delete(channel: Channel)

    companion object {
        const val TABLE_NAME = "channels"

        private const val ORDER_BY_NAME = "ORDER BY ${Channel.FIELD_NAME}"
    }
}
