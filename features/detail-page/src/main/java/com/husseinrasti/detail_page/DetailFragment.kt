package com.husseinrasti.detail_page

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.husseinrasti.detail_page.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment Created by Sarah
 */

@AndroidEntryPoint
class DetailFragment : Fragment() {

   /* private val args: DetailFragmentArgs by navArgs()*/

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* val nameOFCoin = args.nameOfCoin
        binding.name0fCoin.text = nameOFCoin

        val currentPrice = args.currentPrice
        binding.currentPrice.text = currentPrice.toString()*/

        binding.name0fCoin.text = arguments?.getString("nameOfCoins")

    }

}