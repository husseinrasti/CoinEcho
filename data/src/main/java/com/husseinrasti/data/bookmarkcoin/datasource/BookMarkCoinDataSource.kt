package com.husseinrasti.data.bookmarkcoin.datasource

import androidx.paging.PagingSource
import com.husseinrasti.data.bookmarkcoin.dao.BookMarkCoinDao
import com.husseinrasti.domain.bookmark.entity.BookMarkEntity
import com.husseinrasti.domain.bookmark.entity.BookmarkCoinEntity
import javax.inject.Inject

class BookMarkCoinDataSource @Inject constructor(private val dao: BookMarkCoinDao) {

    suspend fun updateBookMarkId(bookmarkCoinEntity: BookmarkCoinEntity, bookMarkState: Int) {
        if (bookMarkState == 1) {
            dao.insertBookMarkId(bookmarkCoinEntity)
        } else {
            dao.delete(bookmarkCoinEntity.id)
        }
    }
    suspend fun selectAllBookMarksIds(): List<String> {
        return dao.selectAllBookMarksIds()
    }

    fun selectBookMarks():PagingSource<Int,BookMarkEntity>{
        return dao.selectBookMarks()
    }

    suspend fun updateBookMark(bookmarkEntity: BookMarkEntity, bookMarkState: Int) {
        if (bookMarkState == 1) {
            dao.insertBookMark(bookmarkEntity)
        } else {
            dao.deleteBookmark(bookmarkEntity.id)
        }
    }
}