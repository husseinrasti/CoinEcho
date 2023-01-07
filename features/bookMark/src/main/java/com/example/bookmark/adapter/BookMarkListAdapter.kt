package com.example.bookmark.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.husseinrasti.bookMark.R
import com.husseinrasti.bookMark.databinding.AdapterItemBookmarksBinding
import com.husseinrasti.core.extensions.load
import com.husseinrasti.core.extensions.toDollar
import com.husseinrasti.core.extensions.toPercent
import com.husseinrasti.domain.bookmark.entity.BookMarkEntity

class BookMarkListAdapter(var OnBookMarkClick :(BookMarkEntity, Int) -> Unit):  PagingDataAdapter<BookMarkEntity, BookMarkListAdapter.ViewHolder>(DiffUtilMarket()) {

    private lateinit var _context: Context

    inner class ViewHolder(
        private val binding:AdapterItemBookmarksBinding) : RecyclerView.ViewHolder(binding.root) {

            private var bookMarked=1
            @SuppressLint("SetTextI18n")
            fun bind(coin: BookMarkEntity) {
                binding.bookmark.setBackgroundDrawable(_context.resources.getDrawable(R.drawable.bookmark2))
                binding.name.text = coin.symbol
                binding.rank.text = coin.marketCapRank.toString()
                binding.price.text = coin.currentPrice.toDollar()
                val percent = coin.priceChangePercentage24h.toPercent()
                binding.percent.text = percent
                binding.bookmark.setOnClickListener(View.OnClickListener {
                        bookMarked =0
                        OnBookMarkClick?.invoke(coin,bookMarked)

                })
                binding.percent.setTextColor(
                    ContextCompat.getColor(
                        _context, if (percent.contains("-")) R.color.red else R.color.green
                    )
                )
                binding.marketCap.text = coin.marketCap.toDollar().split(".")[0]
                binding.logo.load(coin.image)
            }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _context = parent.context
        return ViewHolder(
            AdapterItemBookmarksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    private class DiffUtilMarket : DiffUtil.ItemCallback<BookMarkEntity>() {
        override fun areItemsTheSame(oldItem: BookMarkEntity, newItem:BookMarkEntity): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: BookMarkEntity, newItem: BookMarkEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}