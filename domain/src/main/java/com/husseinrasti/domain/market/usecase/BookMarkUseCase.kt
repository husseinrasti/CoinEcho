package com.husseinrasti.domain.market.usecase

import com.husseinrasti.domain.bookmark.entity.BookmarkCoinEntity
import com.husseinrasti.domain.market.repository.BookMarkRepository
import javax.inject.Inject

class BookMarkUseCase @Inject constructor(val bookMarkRepository: BookMarkRepository) {

     suspend fun addBookMark(bookmarkCoinEntity: BookmarkCoinEntity){
        bookMarkRepository.addBookMark(bookmarkCoinEntity)
    }
}