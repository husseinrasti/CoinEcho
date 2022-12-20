package com.husseinrasti.domain.market.repository


import com.husseinrasti.domain.bookmark.entity.BookmarkCoinEntity


interface BookMarkRepository {
    suspend fun addBookMark(bookmarkCoinEntity: BookmarkCoinEntity)
}