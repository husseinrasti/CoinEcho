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

package com.husseinrasti.core.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


/**
 * Created by Hussein Rasti on 2/24/22.
 */

@SuppressLint("CheckResult")
fun ImageView.load(
    url: String?,
    activity: Activity? = null
) {
    val glideRequest = Glide
        .with(context)
        .load(url)
        .dontAnimate()

    activity?.let {
        glideRequest
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    ActivityCompat.startPostponedEnterTransition(it)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    ActivityCompat.startPostponedEnterTransition(it)
                    return false
                }
            })
    }

    glideRequest.into(this)
}
