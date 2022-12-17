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

package com.husseinrasti.data.remoteKeys.dao
import androidx.room.*
import com.husseinrasti.data.remoteKeys.entity.RemoteKeysEntity
/**
 * Created by Hussein Rasti on 2/23/22.
 */
@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey:List<RemoteKeysEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneKey(remoteKey:RemoteKeysEntity)

    @Delete
    suspend fun delete(remoteKey: RemoteKeysEntity)

    @Query("SELECT * FROM remote_keys WHERE type = :type")
    suspend fun fetchKeysByType(type: String): RemoteKeysEntity?

    @Query("DELETE FROM remote_keys")
    suspend fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(list: List<RemoteKeysEntity>?)

    @Query("select * from remote_keys where type = :type")
  suspend fun getAllRemoteKeys(type: String): RemoteKeysEntity?

    @Query("delete from remote_keys")
    suspend fun deleteAllRemoteKeys()
}