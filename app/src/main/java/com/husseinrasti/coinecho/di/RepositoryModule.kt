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

package com.husseinrasti.coinecho.di

import android.content.res.Resources
import com.husseinrasti.data.market.datasource.MarketPagingSource
import com.husseinrasti.data.market.repository.MarketRepositoryImpl
import com.husseinrasti.domain.market.repository.MarketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


/**
 * Created by Hussein Rasti on 2/22/22.
 */
@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMarketRepository(resources: Resources, pagingSource: MarketPagingSource): MarketRepository {
        return MarketRepositoryImpl(
            resources = resources,
            pagingSource = pagingSource
        )
    }

}