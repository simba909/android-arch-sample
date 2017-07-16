package se.jarbrant.androidarchsample.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Simon Jarbrant
 * Created on 2017-05-20.
 */
@Entity(tableName = Channel.TABLE_NAME)
data class Channel(
        @PrimaryKey
        var id: Int,

        @ColumnInfo(name = FIELD_NAME)
        var name: String,

        @ColumnInfo(name = FIELD_IMAGE)
        var image: String?,

        @ColumnInfo(name = FIELD_TYPE)
        var type: Int,

        @ColumnInfo(name = "color")
        var color: Int
) {
    companion object {
        const val TABLE_NAME = "channels"

        const val FIELD_ID = "id"
        const val FIELD_NAME = "name"
        const val FIELD_IMAGE = "image"
        const val FIELD_TYPE = "type"

        const val TYPE_NATIONAL = 0
        const val TYPE_LOCAL = 1
        const val TYPE_MINORITY = 2
        const val TYPE_WEB = 3
        const val TYPE_EXTRA = 4
        const val TYPE_NONE = -1
    }
}
