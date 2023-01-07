package com.husseinrasti.domain.bookmark.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.husseinrasti.domain.bookmark.entity.BookMarkEntity
import com.husseinrasti.domain.coin.entity.CoinEntity
import kotlinx.coroutines.flow.Flow

interface BookMarkPageRepository {
    suspend fun getBookMarkList(): Flow<PagingData<BookMarkEntity>>
    suspend fun updateBookMark(bookMarkEntity: BookMarkEntity,bookmarkState:Int)
}