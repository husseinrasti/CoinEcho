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

package com.husseinrasti.data.remoteKeys.datasource


import com.husseinrasti.data.remoteKeys.dao.RemoteKeysDao
import com.husseinrasti.data.remoteKeys.entity.RemoteKeysEntity
import javax.inject.Inject


/**
 * Created by Hussein Rasti on 2/23/22.
 */
class RemoteKeysDataSource @Inject constructor(private val dao: RemoteKeysDao) {

    suspend fun insert(remoteKey: RemoteKeysEntity) {
        dao.insert(remoteKey)
    }

    suspend fun delete(remoteKey: RemoteKeysEntity) {
        dao.delete(remoteKey)
    }

    suspend fun fetchKeysByType(type: String): RemoteKeysEntity? {
        return dao.fetchKeysByType(type)
    }

    suspend fun clear() {
        dao.clear()
    }

}