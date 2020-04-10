package com.github.satoshun.example.merger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.github.satoshun.example.databinding.MainActBinding

class MergerActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val manager = LinearLayoutManager(this)
    binding.recycler.layoutManager = manager

    val mergeAdapter = MergeAdapter()
    binding.recycler.adapter = mergeAdapter
  }
}
