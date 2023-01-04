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

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.husseinrasti.core.extensions.load
import com.husseinrasti.core.extensions.toDollar
import com.husseinrasti.core.extensions.toPercent
import com.husseinrasti.domain.coin.entity.CoinEntity
import com.husseinrasti.market.databinding.AdapterItemMarketBinding


/**
 * Created by Hussein Rasti on 2/24/22.
 */
class MarketAdapter(var clickListener : ((CoinEntity.Item) -> Unit)?): PagingDataAdapter<CoinEntity.Item,
        MarketAdapter.ViewHolder>(DiffUtilMarket()) {

    private lateinit var _context: Context

    var clickListener2: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _context = parent.context
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

        @SuppressLint("SetTextI18n")
        fun bind(coin: CoinEntity.Item) {
            binding.name.text = coin.symbol
            binding.rank.text = coin.marketCapRank.toString()
            binding.price.text = coin.currentPrice.toDollar()
            val percent = coin.priceChangePercentage24h.toPercent()
            binding.percent.text = percent
            binding.percent.setTextColor(
                ContextCompat.getColor(
                    _context, if (percent.contains("-")) R.color.red else R.color.green
                )
            )
            binding.marketCap.text = coin.marketCap.toDollar().split(".")[0]
            binding.logo.load(coin.image)

            binding.name.setOnClickListener {
                clickListener?.invoke(coin)
                Log.d("CLICK", "Listener works")
            }

            binding.rank.setOnClickListener {
                clickListener2?.invoke(coin.id)
            }
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