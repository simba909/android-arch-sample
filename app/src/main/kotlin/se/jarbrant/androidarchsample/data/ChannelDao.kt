package se.jarbrant.androidarchsample.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import se.jarbrant.androidarchsample.data.Channel.Companion.TABLE_NAME

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
@Dao
interface ChannelDao {

    @Query("SELECT * FROM $TABLE_NAME $ORDER_BY_NAME")
    fun load(): LiveData<List<Channel>>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${Channel.FIELD_ID} IN (:channelIds) $ORDER_BY_NAME")
    fun load(channelIds: IntArray): LiveData<List<Channel>>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${Channel.FIELD_TYPE} = :type $ORDER_BY_NAME")
    fun load(type: Int): LiveData<List<Channel>>

    @Insert(onConflict = REPLACE)
    fun save(vararg channels: Channel)

    @Update(onConflict = REPLACE)
    fun update(channel: Channel)

    @Delete
    fun delete(channel: Channel)

    companion object {
        private const val ORDER_BY_NAME = "ORDER BY ${Channel.FIELD_NAME}"
    }
}
