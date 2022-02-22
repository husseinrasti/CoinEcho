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

import android.app.Application
import android.content.res.Resources
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.husseinrasti.coinecho.cache.AppDatabase
import com.husseinrasti.core.BuildConfig
import com.husseinrasti.core.network.BaseHttpClient
import com.husseinrasti.core.network.BaseRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Created by Hussein Rasti on 2/22/22.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun resources(application: Application): Resources {
        return application.resources
    }

    @Provides
    @Singleton
    fun gson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun okHttpClient(baseHttpClient: BaseHttpClient): OkHttpClient {
        return baseHttpClient.okHttpClient
    }

    @Provides
    fun retrofit(baseRetrofit: BaseRetrofit): Retrofit {
        return baseRetrofit.retrofit
    }

    @Provides
    @Singleton
    fun database(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, BuildConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}