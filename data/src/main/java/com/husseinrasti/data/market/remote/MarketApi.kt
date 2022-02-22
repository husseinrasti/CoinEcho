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

package com.husseinrasti.data.market.remote

import com.husseinrasti.core.network.Urls
import com.husseinrasti.data.coin.entity.CoinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Hussein Rasti on 2/22/22.
 */
interface MarketApi {

    @GET(Urls.MARKETS)
    suspend fun getMarkets(
        @Query("vs_currency") currency: String,
        @Query("category") category: String,
        @Query("order") order: String,
        @Query("per_page") limit: Int,
        @Query("page") page: Int,
        @Query("sparkline") sparkline: Boolean
    ): Response<List<CoinResponse>>

}