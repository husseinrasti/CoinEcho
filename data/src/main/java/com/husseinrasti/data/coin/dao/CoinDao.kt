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

package com.husseinrasti.data.coin.dao


import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.husseinrasti.domain.coin.entity.CoinEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Hussein Rasti on 2/23/22.
 */
@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CoinEntity.Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<CoinEntity.Item>?)

    @Query("SELECT * FROM tbl_coin WHERE id=:id")
    fun select(id: String): Flow<CoinEntity.Item>

    @Query("SELECT * FROM tbl_coin")
    fun select(): Flow<List<CoinEntity.Item>>

    @Query("DELETE FROM tbl_coin")
    suspend fun clearAll()

    @Query("SELECT * FROM tbl_coin")
     fun getCoin():PagingSource<Int,CoinEntity.Item>

}