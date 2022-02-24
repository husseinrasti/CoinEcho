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
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.husseinrasti.core.extensions.load
import com.husseinrasti.domain.coin.entity.CoinEntity
import com.husseinrasti.market.databinding.AdapterItemMarketBinding


/**
 * Created by Hussein Rasti on 2/24/22.
 */
class MarketAdapter() : PagingDataAdapter<CoinEntity.Item, MarketAdapter.ViewHolder>(DiffUtilMarket()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterItemMarketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    inner class ViewHolder(
        private val binding: AdapterItemMarketBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: CoinEntity.Item) {
            binding.name.text = coin.name
            binding.price.text = coin.currentPrice.toString()
            binding.logo.load(coin.image)
        }

    }


    private class DiffUtilMarket : DiffUtil.ItemCallback<CoinEntity.Item>() {
        override fun areItemsTheSame(oldItem: CoinEntity.Item, newItem: CoinEntity.Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CoinEntity.Item, newItem: CoinEntity.Item): Boolean {
            return oldItem.id == newItem.id
        }

    }

}