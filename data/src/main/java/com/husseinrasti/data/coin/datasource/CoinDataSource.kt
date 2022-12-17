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

package com.husseinrasti.data.coin.datasource

import androidx.paging.PagingSource
import com.husseinrasti.data.coin.dao.CoinDao
import com.husseinrasti.domain.coin.entity.CoinEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 2/24/22.
 */
class CoinDataSource @Inject constructor(
    private val dao: CoinDao
) {

    suspend fun insertList(list: List<CoinEntity.Item>?) {
        dao.insert(list)
    }

    suspend fun deleteAll() {
        return dao.clearAll()
    }

    fun getCoin():PagingSource<Int, CoinEntity.Item>{
        return dao.getCoin()
    }
}