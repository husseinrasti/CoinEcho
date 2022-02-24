/*
 * Copyright (C) 2022  The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.husseinrasti.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.husseinrasti.core.exceptions.Failure
import com.husseinrasti.domain.coin.entity.CoinEntity
import com.husseinrasti.domain.market.entity.MarketEntity
import com.husseinrasti.domain.market.usecase.GetMarketsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 2/24/22.
 */
@HiltViewModel
class MarketViewModel @Inject constructor(
    private val getMarketsUseCase: GetMarketsUseCase
) : ViewModel() {

    private val _error = MutableLiveData<Failure>()
    val error: LiveData<Failure> = _error
    fun onError(failure: Failure) {
        _error.postValue(failure)
    }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    fun onLoading(isLoading: Boolean) {
        _loading.postValue(isLoading)
    }

    private val _markets = MutableLiveData<PagingData<CoinEntity.Item>>()
    val markets: LiveData<PagingData<CoinEntity.Item>> = _markets
    suspend fun getMarkets(body: MarketEntity.Body) {
        onLoading(true)
        getMarketsUseCase(body)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .catch {
                onLoading(false)
                onError(Failure.Unknown)
            }
            .collectLatest {
                onLoading(false)
                _markets.postValue(it)
            }
    }

}