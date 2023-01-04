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

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.husseinrasti.core.extensions.visibility
import com.husseinrasti.core.model.Failure
import com.husseinrasti.core.model.toFailure
import com.husseinrasti.domain.coin.entity.CoinEntity
import com.husseinrasti.domain.market.entity.MarketEntity
import com.husseinrasti.market.databinding.FragmentMarketBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Hussein Rasti on 2/24/22.
 */
@AndroidEntryPoint
class MarketFragment : Fragment() {

    private val viewModel: MarketViewModel by viewModels()

    private lateinit var adapter: MarketAdapter

    private var _binding: FragmentMarketBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.getMarkets(MarketEntity.Body(currency = "usd"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickListener: (CoinEntity.Item) -> Unit = { coin ->
            Toast.makeText(context, "coin name ${coin.currentPrice}", Toast.LENGTH_SHORT).show()

            Log.d("Click","wooorks")

            val request = NavDeepLinkRequest.Builder
                .fromUri("myApp://com.CoinEcho/detailFragment?nameOfCoin=${coin.name}&currentPrice=${coin.currentPrice}&marketCap=${coin.marketCap}&percent=${coin.marketCapChangePercentage24h}".toUri())
                .build()
            findNavController().navigate(request)
        }

        adapter = MarketAdapter(clickListener)

      /*  adapter.clickListener = {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://CoinEcho/detailFragment?nameOfCoin=${coin.name}".toUri())
                .build()
            view.findNavController().navigate(request)
        }
        adapter.clickListener2 = {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://CoinEcho/detailFragment?nameOfCoin=${coin.name}".toUri())
                .build()
            view.findNavController().navigate(request)
        }*/

        adapter.addLoadStateListener { adapterLoadingErrorHandling(it) }
        adapter.withLoadStateFooter(LoadingStateAdapter { adapter.retry() })
        binding.recycler.adapter = adapter
        binding.recycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        onSetupViewModel()
    }

    private fun onSetupViewModel() {
        viewModel.markets.observe(viewLifecycleOwner) { adapter.submitData(lifecycle, it) }
        viewModel.error.observe(viewLifecycleOwner) { onShowError(it) }
        viewModel.loading.observe(viewLifecycleOwner) { onLoading(it) }
    }

    private fun onLoading(isLoading: Boolean) {
        binding.progressBar.visibility(isLoading)
    }

    private fun onShowError(failure: Failure) {
        Toast.makeText(requireContext(), "${failure.message}", Toast.LENGTH_SHORT).show()
    }

    private fun adapterLoadingErrorHandling(combinedLoadStates: CombinedLoadStates) {
        if (combinedLoadStates.refresh is LoadState.Loading) {
            onLoading(true)
        } else {
            onLoading(false)
            val error = when {
                combinedLoadStates.prepend is LoadState.Error -> combinedLoadStates.prepend as LoadState.Error
                combinedLoadStates.source.prepend is LoadState.Error -> combinedLoadStates.prepend as LoadState.Error
                combinedLoadStates.append is LoadState.Error -> combinedLoadStates.append as LoadState.Error
                combinedLoadStates.source.append is LoadState.Error -> combinedLoadStates.append as LoadState.Error
                combinedLoadStates.refresh is LoadState.Error -> combinedLoadStates.refresh as LoadState.Error
                else -> null
            }
            error?.run {
                onShowError(this.error.toFailure())
            }
        }
    }

}