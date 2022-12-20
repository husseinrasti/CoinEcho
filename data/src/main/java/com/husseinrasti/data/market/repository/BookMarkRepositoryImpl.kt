package com.husseinrasti.data.market.repository

import com.husseinrasti.data.bookmarkcoin.datasource.BookMarkCoinDataSource
import com.husseinrasti.domain.bookmark.entity.BookmarkCoinEntity
import com.husseinrasti.domain.market.repository.BookMarkRepository
import javax.inject.Inject

class BookMarkRepositoryImpl @Inject constructor(val bookMarkDao:BookMarkCoinDataSource) :BookMarkRepository {

    override suspend fun addBookMark(bookmarkCoinEntity: BookmarkCoinEntity) {
        bookMarkDao.insertBookMarkId(bookmarkCoinEntity)
    }
}