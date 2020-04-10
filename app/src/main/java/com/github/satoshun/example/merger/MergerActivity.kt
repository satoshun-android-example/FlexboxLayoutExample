package com.github.satoshun.example.merger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.MainActBinding
import com.github.satoshun.example.databinding.MainItemBinding
import com.github.satoshun.merger.DataItem
import com.github.satoshun.merger.Item
import com.github.satoshun.merger.ListItem

class MergerActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val manager = LinearLayoutManager(this)
    binding.recycler.layoutManager = manager

    val mergeAdapter = MergeAdapter()

    mergeAdapter.addAdapter(
      Item(R.layout.main_item) {
        val mainItem = MainItemBinding.bind(itemView)
        mainItem.chip.text = "test"
      }
    )

    val data = listOf("sato", "shun", "desu")
    mergeAdapter.addAdapter(
      ListItem(
        initialData = data,
        layoutId = R.layout.main_item,
        areItemsTheSame = { a, b -> a == b }
      ) { text, _ ->
        val mainItem = MainItemBinding.bind(itemView)
        mainItem.chip.text = text
      }
    )

    mergeAdapter.addAdapter(
      DataItem(
        data = "hello",
        layoutId = R.layout.main_item
      ) { text, _ ->
        val mainItem = MainItemBinding.bind(itemView)
        mainItem.chip.text = text
      }
    )

    binding.recycler.adapter = mergeAdapter
  }
}
