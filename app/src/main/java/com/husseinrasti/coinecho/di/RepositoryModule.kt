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
import com.husseinrasti.coinecho.App
import com.husseinrasti.coinecho.cache.AppDatabase
import com.husseinrasti.data.bookmarkcoin.datasource.BookMarkCoinDataSource
import com.husseinrasti.data.coin.dao.CoinDao
import com.husseinrasti.data.coin.datasource.CoinDataSource
import com.husseinrasti.data.market.datasource.MarketPagingSource
import com.husseinrasti.data.market.remote.MarketApi
import com.husseinrasti.data.market.remoteMediator.MarketRemoteMediator
import com.husseinrasti.data.market.repository.BookMarkRepositoryImpl
import com.husseinrasti.data.market.repository.MarketRepositoryImpl
import com.husseinrasti.data.remoteKeys.datasource.RemoteKeysDataSource
import com.husseinrasti.domain.market.repository.BookMarkRepository
import com.husseinrasti.domain.market.repository.MarketRepository
import com.husseinrasti.domain.market.usecase.BookMarkUseCase
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
    fun provideMarketRepository(resources: Resources, pagingSource: MarketPagingSource,remoteMediator:MarketRemoteMediator,coinDataSource: CoinDataSource,bookMarkDataSource: BookMarkCoinDataSource): MarketRepository {
        return MarketRepositoryImpl(
            resources = resources,
            pagingSource = pagingSource,
            remoteMediator=remoteMediator,
            coinDataSource = coinDataSource,
            bookMarkDao = bookMarkDataSource
        )
    }

    @Provides
    @ViewModelScoped
    fun provideMarketRemoteMediator(coinDataSource: CoinDataSource, remoteKeyDAO:RemoteKeysDataSource,marketApi:MarketApi,database:AppDatabase,bookMarkDataSource:BookMarkCoinDataSource): MarketRemoteMediator {
        return MarketRemoteMediator(
           coinDAO = coinDataSource,
            remoteKeyDAO=remoteKeyDAO,
            marketInterface =marketApi,
            db = database,
            bookMarkDao = bookMarkDataSource
        )
    }

    @Provides
    @ViewModelScoped
    fun provideBookMarkRepository(bookMarkCoinDataSource: BookMarkCoinDataSource,coinDataSource: CoinDataSource): BookMarkRepositoryImpl {
        return BookMarkRepositoryImpl(
       bookMarkDao = bookMarkCoinDataSource,
            coinDao = coinDataSource
        )
    }

    @Provides
    @ViewModelScoped
    fun provideBookMarkUseCase(bookMarkRepository: BookMarkRepositoryImpl): BookMarkUseCase {
        return BookMarkUseCase(
           bookMarkRepository = bookMarkRepository
        )
    }

}