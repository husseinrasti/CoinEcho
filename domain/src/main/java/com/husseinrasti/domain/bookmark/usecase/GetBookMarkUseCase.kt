package com.husseinrasti.domain.bookmark.usecase

import androidx.paging.PagingData
import com.husseinrasti.domain.bookmark.entity.BookMarkEntity
import com.husseinrasti.domain.bookmark.repository.BookMarkPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookMarkUseCase @Inject constructor(private val repository:BookMarkPageRepository) {
    suspend fun getBookMarkList(): Flow<PagingData<BookMarkEntity>> {
        return repository.getBookMarkList()
    }
    suspend fun updateBookMark(bookMarkEntity: BookMarkEntity,bookMarkState:Int){
        repository.updateBookMark(bookMarkEntity,bookMarkState)
    }
}