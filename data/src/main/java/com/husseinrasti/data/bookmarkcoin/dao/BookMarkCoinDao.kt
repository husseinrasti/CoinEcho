package com.husseinrasti.data.bookmarkcoin.dao

import androidx.room.*
import com.husseinrasti.data.bookmarkcoin.entity.BookmarkCoinEntity


@Dao
    interface BookMarkCoinDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertBookMarkId(bookmarkCoinEntity:BookmarkCoinEntity)

        @Delete
        suspend fun delete(bookmarkCoinEntity: BookmarkCoinEntity)

        @Query("delete from bookmark_coin")
        suspend fun deleteAllBookmarks()

        @Query("SELECT * FROM bookmark_coin WHERE id = :id")
        suspend fun selectBookMarkId(id: String): String?
    }
