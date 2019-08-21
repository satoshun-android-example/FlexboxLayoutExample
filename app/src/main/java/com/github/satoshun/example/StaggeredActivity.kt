package com.github.satoshun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.satoshun.example.databinding.StaggeredActBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class StaggeredActivity : AppCompatActivity() {
  private lateinit var binding: StaggeredActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.staggered_act)

    binding.recycler.layoutManager = StaggeredGridLayoutManager(
      100,
      StaggeredGridLayoutManager.VERTICAL
    )
    binding.recycler.adapter = ChipStaggeredAdapter()
  }
}

private class ChipStaggeredAdapter : GroupAdapter<ViewHolder>() {
  init {
    updateChips(mockItems1)
  }

  fun updateChips(items: List<String>) {
    addAll(items.map { MainItem(it) })
  }
}
