package com.github.satoshun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.satoshun.example.databinding.StaggeredActBinding
import com.github.satoshun.example.flexbox2.MainItem
import com.github.satoshun.example.flexbox2.mockItems1
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class StaggeredActivity : AppCompatActivity() {
  private lateinit var binding: StaggeredActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = StaggeredActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.recycler.layoutManager = StaggeredGridLayoutManager(
      100,
      StaggeredGridLayoutManager.VERTICAL
    )
    binding.recycler.adapter = ChipStaggeredAdapter()
  }
}

private class ChipStaggeredAdapter : GroupAdapter<GroupieViewHolder>() {
  init {
    updateChips(mockItems1)
  }

  fun updateChips(items: List<String>) {
    addAll(items.map { MainItem(it) })
  }
}
