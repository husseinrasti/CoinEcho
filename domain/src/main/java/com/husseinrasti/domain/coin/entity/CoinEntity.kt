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

package com.husseinrasti.domain.coin.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Hussein Rasti on 2/22/22.
 */
class CoinEntity {

    @Entity(tableName = "tbl_coin")
    data class Item(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "ath")
        val ath: Double,
        @ColumnInfo(name = "ath_change_percentage")
        val athChangePercentage: Double,
        @ColumnInfo(name = "ath_date")
        val athDate: String,
        @ColumnInfo(name = "circulating_supply")
        val circulatingSupply: Double,
        @ColumnInfo(name = "current_price")
        val currentPrice: Double,
        @ColumnInfo(name = "low_24h")
        val low24h: Double,
        @ColumnInfo(name = "high_24h")
        val high24h: Double,
        @ColumnInfo(name = "image")
        val image: String,
        @ColumnInfo(name = "market_cap")
        val marketCap: Double,
        @ColumnInfo(name = "market_cap_change_24h")
        val marketCapChange24h: Double,
        @ColumnInfo(name = "market_cap_change_percentage_24h")
        val marketCapChangePercentage24h: Double,
        @ColumnInfo(name = "market_cap_rank")
        val marketCapRank: Long,
        @ColumnInfo(name = "max_supply")
        val maxSupply: Double,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "price_change_24h")
        val priceChange24h: Double,
        @ColumnInfo(name = "price_change_percentage_24h")
        val priceChangePercentage24h: Double,
        @ColumnInfo(name = "symbol")
        val symbol: String,
        @ColumnInfo(name = "total_supply")
        val totalSupply: Double,
        @ColumnInfo(name = "total_volume")
        val totalVolume: Double,
        @ColumnInfo(name = "sparkline_in_7d")
        val sparklineIn7d: List<Double>
    )

}