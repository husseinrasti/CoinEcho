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

package com.husseinrasti.data.market.datasource

import android.content.res.Resources
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.husseinrasti.core.network.fetchPage
import com.husseinrasti.core.utils.STARTING_PAGE_INDEX
import com.husseinrasti.data.market.remote.MarketApi
import com.husseinrasti.domain.coin.entity.CoinEntity
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 2/24/22.
 */
class MarketPagingSource @Inject constructor(
    private val resources: Resources,
    private val api: MarketApi
) : PagingSource<Int, CoinEntity.Item>() {

    var currency: String = ""
    var category: String? = null
    var order: String? = null
    var sparkline: Boolean = false

    override fun getRefreshKey(state: PagingState<Int, CoinEntity.Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinEntity.Item> {
        return fetchPage(
            resources = resources,
            position = params.key ?: STARTING_PAGE_INDEX,
            loadSize = params.loadSize,
            fetch = {
                api.getMarkets(
                    page = params.key ?: STARTING_PAGE_INDEX,
                    limit = params.loadSize,
                    category = category,
                    currency = currency,
                    order = order,
                    sparkline = sparkline,
                )
            }
        )
    }

}