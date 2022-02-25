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

package com.husseinrasti.market

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.husseinrasti.core.extensions.visibility
import com.husseinrasti.market.databinding.LoadStateViewBinding


/**
 * Created by Hussein Rasti on 2/24/22.
 */
class LoadingStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadingStateAdapter.LoadingStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder = LoadingStateViewHolder(
        LoadStateViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    inner class LoadingStateViewHolder(
        private val binding: LoadStateViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.retryLoadState.visibility(loadState !is LoadState.Loading)
            binding.errorMessageLoadState.visibility(loadState !is LoadState.Loading)
            binding.progressLoadState.visibility(loadState is LoadState.Loading)
            if (loadState is LoadState.Error) binding.errorMessageLoadState.text = loadState.error.localizedMessage
            binding.retryLoadState.setOnClickListener { retry.invoke() }
        }
    }

}