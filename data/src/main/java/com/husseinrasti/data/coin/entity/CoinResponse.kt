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

package com.husseinrasti.data.coin.entity

import com.google.gson.annotations.SerializedName
import com.husseinrasti.core.mapper.ResponseObject
import com.husseinrasti.domain.coin.entity.CoinEntity

data class CoinResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("ath")
    val ath: Double?,
    @SerializedName("ath_change_percentage")
    val athChangePercentage: Double?,
    @SerializedName("ath_date")
    val athDate: String?,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double?,
    @SerializedName("current_price")
    val currentPrice: Double?,
    @SerializedName("low_24h")
    val low24h: Double?,
    @SerializedName("high_24h")
    val high24h: Double?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("market_cap")
    val marketCap: Double?,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: Double?,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double?,
    @SerializedName("market_cap_rank")
    val marketCapRank: Long?,
    @SerializedName("max_supply")
    val maxSupply: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price_change_24h")
    val priceChange24h: Double?,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("total_supply")
    val totalSupply: Double?,
    @SerializedName("total_volume")
    val totalVolume: Double?,
    @SerializedName("sparkline_in_7d")
    val sparklineIn7d: Sparkline?
) : ResponseObject<CoinEntity.Item> {

    data class Sparkline(
        @SerializedName("price")
        val price: List<Double>?
    )

    override fun toDomain(): CoinEntity.Item {
        return CoinEntity.Item(
            id = id ?: "",
            ath = ath ?: 0.0,
            athChangePercentage = athChangePercentage ?: 0.0,
            athDate = athDate ?: "",
            circulatingSupply = circulatingSupply ?: 0.0,
            currentPrice = currentPrice ?: 0.0,
            low24h = low24h ?: 0.0,
            high24h = high24h ?: 0.0,
            image = image ?: "",
            marketCap = marketCap ?: 0.0,
            marketCapChange24h = marketCapChange24h ?: 0.0,
            marketCapChangePercentage24h = marketCapChangePercentage24h ?: 0.0,
            marketCapRank = marketCapRank ?: 0,
            maxSupply = maxSupply ?: 0.0,
            name = name ?: "",
            priceChange24h = priceChange24h ?: 0.0,
            priceChangePercentage24h = priceChangePercentage24h ?: 0.0,
            symbol = symbol ?: "",
            totalSupply = totalSupply ?: 0.0,
            totalVolume = totalVolume ?: 0.0,
            sparklineIn7d = sparklineIn7d?.price ?: listOf()
        )
    }

}