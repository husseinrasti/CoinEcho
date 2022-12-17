package com.husseinrasti.data.bookmarkcoin.dao

import androidx.room.*
import com.husseinrasti.data.remoteKeys.entity.RemoteKeysEntity

class BookMarkCoinDao {
    @Dao
    interface RemoteKeysDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertBookMarkId(id:String)

        @Delete
        suspend fun delete(id:String)

        @Query("delete from bookmark_coin")
        suspend fun deleteAllBookmarks()

        @Query("SELECT * FROM bookmark_coin WHERE id = :id")
        suspend fun selectBookMarkId(id: String): RemoteKeysEntity?
    }
}