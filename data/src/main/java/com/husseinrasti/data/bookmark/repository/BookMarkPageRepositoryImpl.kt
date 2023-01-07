package com.husseinrasti.data.bookmark.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.husseinrasti.core.model.NETWORK_PAGE_SIZE
import com.husseinrasti.data.bookmarkcoin.datasource.BookMarkCoinDataSource
import com.husseinrasti.data.coin.datasource.CoinDataSource
import com.husseinrasti.domain.bookmark.entity.BookMarkEntity
import com.husseinrasti.domain.bookmark.repository.BookMarkPageRepository
import com.husseinrasti.domain.coin.entity.CoinEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookMarkPageRepositoryImpl @Inject constructor(private val coinDao:CoinDataSource, private val bookMarkDao:BookMarkCoinDataSource):BookMarkPageRepository {

    override suspend fun getBookMarkList(): Flow<PagingData<BookMarkEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { bookMarkDao.selectBookMarks()
            }
        ).flow
    }

    override suspend fun updateBookMark(bookMarkEntity: BookMarkEntity, bookmarkState: Int) {
        bookMarkDao.updateBookMarkId(bookMarkEntity.toBookMarkCoinEntity(),bookmarkState)
        bookMarkDao.updateBookMark(bookMarkEntity,bookmarkState)
        coinDao.updateBookMark(bookmarkState,bookMarkEntity.id)
    }

}