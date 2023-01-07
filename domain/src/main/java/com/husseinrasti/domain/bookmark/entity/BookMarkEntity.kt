package com.husseinrasti.domain.bookmark.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_entity")
class BookMarkEntity(
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
    val sparklineIn7d: List<Double>,
) {

    fun toBookMarkCoinEntity(): BookmarkCoinEntity {
        return BookmarkCoinEntity(id = id)
    }

}