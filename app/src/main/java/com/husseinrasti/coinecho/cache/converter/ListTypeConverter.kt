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

package com.husseinrasti.coinecho.cache.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by Hussein Rasti on 2/24/22.
 *
 * <h1> List Type Converter </h1>
 * This class convert a <code> List </code> type value to <code>String</code>
 * and save it to database and convert a <code>String</code> and return it
 */
class ListTypeConverter {

    /**
     * @param value gets a <code>String</code>
     * @return json from list of Double
     * @see Double
     */
    @TypeConverter
    fun fromListDoubleJson(value: String?): List<Double>? {
        val typeList = object : TypeToken<List<Double>>() {}.type
        return Gson().fromJson(value, typeList)
    }

    /**
     * @param list gets a list of Double type value
     * @return json from list of Double
     * @see Double
     */
    @TypeConverter
    fun toListDoubleJson(list: List<Double>?): String? = Gson().toJson(list)

}