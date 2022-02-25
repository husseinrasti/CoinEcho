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

package com.husseinrasti.domain.market.repository

import androidx.paging.PagingData
import com.husseinrasti.domain.coin.entity.CoinEntity
import com.husseinrasti.domain.market.entity.MarketEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Hussein Rasti on 2/22/22.
 */
interface MarketRepository {

    suspend fun getMarkets(body: MarketEntity.Body): Flow<PagingData<CoinEntity.Item>>

}