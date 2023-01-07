package com.example.bookmark

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmark.adapter.BookMarkListAdapter
import com.husseinrasti.bookMark.R
import com.husseinrasti.bookMark.databinding.FragmentBookMarkListBinding
import com.husseinrasti.domain.bookmark.entity.BookMarkEntity
import com.husseinrasti.domain.bookmark.entity.BookmarkCoinEntity
import com.husseinrasti.domain.coin.entity.CoinEntity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Observer

@AndroidEntryPoint
class BookMarkListFragment : Fragment() {


    private val viewModel: BookMarkViewModel by viewModels()
    private lateinit var adapter: BookMarkListAdapter

    private var _binding:FragmentBookMarkListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.getBookMarkList()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentBookMarkListBinding.inflate(inflater, container, false)
        return binding.root
    }

    val OnBookMarkClickState:(BookMarkEntity, Int)->Unit={ coin, state->
        viewModel.updateBookMark(coin,state)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bookmarkRecycler.setLayoutManager(LinearLayoutManager(context));
        adapter = BookMarkListAdapter(OnBookMarkClickState)
        binding.bookmarkRecycler.adapter = adapter
        binding.bookmarkRecycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        onSetupViewModel()

    }
    private fun onSetupViewModel() {
        viewModel.bookMarks.observe(viewLifecycleOwner) { adapter.submitData(lifecycle, it) }
    }
}