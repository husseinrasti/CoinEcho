package com.husseinrasti.data.bookmarkcoin.dao

import androidx.room.*
import com.husseinrasti.domain.bookmark.entity.BookmarkCoinEntity


@Dao
interface BookMarkCoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookMarkId(bookmarkCoinEntity: BookmarkCoinEntity)

    @Query("delete from bookmark_coin where id = :id ")
    suspend fun delete(id:String)

    @Query("delete from bookmark_coin")
    suspend fun deleteAllBookmarks()

    @Query("SELECT * FROM bookmark_coin WHERE id = :id")
    suspend fun selectBookMarkId(id: String): String?

    @Query("SELECT id FROM bookmark_coin")
    suspend fun selectAllBookMarksIds():List<String>

}
