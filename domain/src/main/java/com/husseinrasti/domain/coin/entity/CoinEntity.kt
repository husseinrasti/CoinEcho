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

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/**
 * Created by Hussein Rasti on 2/22/22.
 */

class CoinEntity : java.io.Serializable{
    @Parcelize
    @Entity(tableName = "tbl_coin")
    data class Item(
        @field:SerializedName("id")
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        val id: String,
        @field:SerializedName("ath")
        @ColumnInfo(name = "ath")
        val ath: Double,
        @field:SerializedName("ath_change_percentage")
        @ColumnInfo(name = "ath_change_percentage")
        val athChangePercentage: Double,
        @field:SerializedName("ath_date")
        @ColumnInfo(name = "ath_date")
        val athDate: String,
        @field:SerializedName("circulating_supply")
        @ColumnInfo(name = "circulating_supply")
        val circulatingSupply: Double,
        @field:SerializedName("current_price")
        @ColumnInfo(name = "current_price")
        val currentPrice: Double,
        @field:SerializedName("low_24h")
        @ColumnInfo(name = "low_24h")
        val low24h: Double,
        @field:SerializedName("high_24h")
        @ColumnInfo(name = "high_24h")
        val high24h: Double,
        @field:SerializedName("image")
        @ColumnInfo(name = "image")
        val image: String,
        @field:SerializedName("market_cap")
        @ColumnInfo(name = "market_cap")
        val marketCap: Double,
        @field:SerializedName("market_cap_change_24h")
        @ColumnInfo(name = "market_cap_change_24h")
        val marketCapChange24h: Double,
        @field:SerializedName("market_cap_change_percentage_24h")
        @ColumnInfo(name = "market_cap_change_percentage_24h")
        val marketCapChangePercentage24h: Double,
        @field:SerializedName("market_cap_rank")
        @ColumnInfo(name = "market_cap_rank")
        val marketCapRank: Long,
        @field:SerializedName("max_supply")
        @ColumnInfo(name = "max_supply")
        val maxSupply: Double,
        @field:SerializedName("name")
        @ColumnInfo(name = "name")
        val name: String,
        @field:SerializedName("price_change_24h")
        @ColumnInfo(name = "price_change_24h")
        val priceChange24h: Double,
        @field:SerializedName("price_change_percentage_24h")
        @ColumnInfo(name = "price_change_percentage_24h")
        val priceChangePercentage24h: Double,
        @field:SerializedName("symbol")
        @ColumnInfo(name = "symbol")
        val symbol: String,
        @field:SerializedName("total_supply")
        @ColumnInfo(name = "total_supply")
        val totalSupply: Double,
        @field:SerializedName("total_volume")
        @ColumnInfo(name = "total_volume")
        val totalVolume: Double,
        @field:SerializedName("sparkline_in_7d")
        @ColumnInfo(name = "sparkline_in_7d")
        val sparklineIn7d: List<Double>
    ) : Parcelable

}