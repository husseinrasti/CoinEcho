package com.example.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.husseinrasti.bookMark.databinding.ActivityBookMarkListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookMarkListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookMarkListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookMarkListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}