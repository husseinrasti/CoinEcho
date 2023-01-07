package com.husseinrasti.data.market.repository

import com.husseinrasti.data.bookmarkcoin.datasource.BookMarkCoinDataSource
import com.husseinrasti.data.coin.datasource.CoinDataSource
import com.husseinrasti.domain.bookmark.entity.BookMarkEntity
import com.husseinrasti.domain.market.repository.BookMarkRepository
import javax.inject.Inject

class BookMarkRepositoryImpl @Inject constructor(val bookMarkDao:BookMarkCoinDataSource,val coinDao:CoinDataSource) :BookMarkRepository {

    override suspend fun addBookMark(bookmarkEntity: BookMarkEntity, bookMarkState: Int) {
        bookMarkDao.updateBookMarkId(bookmarkEntity.toBookMarkCoinEntity(),bookMarkState)
        bookMarkDao.updateBookMark(bookmarkEntity,bookMarkState)
        coinDao.updateBookMark(bookMarkState,bookmarkEntity.id)
    }
}