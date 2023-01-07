package com.husseinrasti.domain.market.repository


import com.husseinrasti.domain.bookmark.entity.BookMarkEntity
import com.husseinrasti.domain.bookmark.entity.BookmarkCoinEntity


interface BookMarkRepository {
    suspend fun addBookMark(bookmarkEntity: BookMarkEntity,bookMarkState:Int)
}