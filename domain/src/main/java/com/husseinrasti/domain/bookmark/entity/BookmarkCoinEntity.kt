package com.husseinrasti.domain.bookmark.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "bookmark_coin")
data class BookmarkCoinEntity(
    @PrimaryKey(autoGenerate = false)
    @NotNull
    @ColumnInfo(name = "id")
    val id: String

    )
