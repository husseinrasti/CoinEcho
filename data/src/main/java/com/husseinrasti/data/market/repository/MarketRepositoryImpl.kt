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

package com.husseinrasti.data.market.repository

import android.content.res.Resources
import androidx.lifecycle.flowWithLifecycle
import androidx.paging.*
import com.husseinrasti.core.model.NETWORK_PAGE_SIZE
import com.husseinrasti.data.bookmarkcoin.datasource.BookMarkCoinDataSource
import com.husseinrasti.data.coin.dao.CoinDao
import com.husseinrasti.data.coin.datasource.CoinDataSource
import com.husseinrasti.data.market.datasource.MarketPagingSource
import com.husseinrasti.data.market.remoteMediator.MarketRemoteMediator
import com.husseinrasti.domain.coin.entity.CoinEntity
import com.husseinrasti.domain.market.entity.MarketEntity
import com.husseinrasti.domain.market.repository.MarketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 2/23/22.
 */
class MarketRepositoryImpl @Inject constructor(
    private val resources: Resources,
    private val pagingSource: MarketPagingSource,
    private val remoteMediator: MarketRemoteMediator,
    private val coinDataSource: CoinDataSource,
    private val bookMarkDao:BookMarkCoinDataSource
) : MarketRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getMarkets(body: MarketEntity.Body): Flow<PagingData<CoinEntity.Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { coinDataSource.getCoin()
//                pagingSource.apply {
//                    category = body.category
//                    currency = body.currency
//                    order = body.order
//                    sparkline = body.sparkline
//                }
            }
        ).flow
    }

}