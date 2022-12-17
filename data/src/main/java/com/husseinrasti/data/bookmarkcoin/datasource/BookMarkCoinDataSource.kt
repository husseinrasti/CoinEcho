package com.husseinrasti.data.bookmarkcoin.datasource

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.husseinrasti.data.bookmarkcoin.dao.BookMarkCoinDao
import com.husseinrasti.data.bookmarkcoin.entity.BookmarkCoinEntity
import com.husseinrasti.data.remoteKeys.entity.RemoteKeysEntity
import javax.inject.Inject

class BookMarkCoinDataSource @Inject constructor(private val dao:BookMarkCoinDao) {

    suspend fun insertBookMarkId(bookmarkCoinEntity: BookmarkCoinEntity){
        dao.insertBookMarkId(bookmarkCoinEntity)
    }

    suspend fun delete(bookmarkCoinEntity: BookmarkCoinEntity){
        dao.delete(bookmarkCoinEntity)
    }

    suspend fun deleteAllBookmarks(){
       dao.deleteAllBookmarks()
    }

    suspend fun selectBookMarkId(id: String): String?{
       return dao.selectBookMarkId(id)
    }
}