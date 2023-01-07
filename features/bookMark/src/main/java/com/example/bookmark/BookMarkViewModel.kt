package com.example.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.husseinrasti.core.model.Failure
import com.husseinrasti.domain.bookmark.entity.BookMarkEntity
import com.husseinrasti.domain.bookmark.repository.BookMarkPageRepository
import com.husseinrasti.domain.coin.entity.CoinEntity
import com.husseinrasti.domain.market.usecase.BookMarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(private val bookMarkUseCase:BookMarkPageRepository): ViewModel() {

    private val _bookMarks = MutableLiveData<PagingData<BookMarkEntity>>()
    val bookMarks: LiveData<PagingData<BookMarkEntity>> = _bookMarks

    suspend fun getBookMarkList() {

        bookMarkUseCase.getBookMarkList()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .catch {
            }
            .collectLatest {
                _bookMarks.postValue(it)
            }
    }
    fun updateBookMark(bookMarkEntity: BookMarkEntity,bookMarkState:Int){
        viewModelScope.launch{
            bookMarkUseCase.updateBookMark(bookMarkEntity, bookMarkState)
        }
    }
}