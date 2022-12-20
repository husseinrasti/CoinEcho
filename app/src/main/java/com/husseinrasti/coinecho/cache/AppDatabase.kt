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

package com.husseinrasti.coinecho.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.husseinrasti.coinecho.cache.converter.ListTypeConverter
import com.husseinrasti.data.bookmarkcoin.dao.BookMarkCoinDao
import com.husseinrasti.domain.bookmark.entity.BookmarkCoinEntity
import com.husseinrasti.data.coin.dao.CoinDao
import com.husseinrasti.data.remoteKeys.dao.RemoteKeysDao
import com.husseinrasti.data.remoteKeys.entity.RemoteKeysEntity
import com.husseinrasti.domain.coin.entity.CoinEntity


/**
 * Created by Hussein Rasti on 2/22/22.
 *
 * <h1>AppDatabase</h1>
 * This class create the database for the application
 * below annotation is database version and the tables
 */
@Database(
    entities = [CoinEntity.Item::class,RemoteKeysEntity::class, BookmarkCoinEntity::class],
    version = 1,
    exportSchema = false
)
/**
 * This is the converter for the tables
 * Whenever you want save a value that its not valid for sqlite
 */
@TypeConverters(ListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Create all table query classes that calls dao
     * Data access object with these classes you can extract data from database
     */

    abstract fun coinDao(): CoinDao
    abstract fun remotekeyDao(): RemoteKeysDao
    abstract fun BookMarkCoinDao(): BookMarkCoinDao

}