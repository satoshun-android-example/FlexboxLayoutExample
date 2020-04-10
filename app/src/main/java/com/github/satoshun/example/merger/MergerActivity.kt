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
import com.github.satoshun.merger.addAdapters
import com.github.satoshun.merger.swap

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

    val data = listOf(
      User(id = "1", name = "sato"),
      User(id = "2", name = "shun"),
      User(id = "3", name = "desu")
    )
    mergeAdapter.addAdapter(
      ListItem(
        initialData = data,
        layoutId = R.layout.main_item,
        sameItemKey = { it.id }
      ) { user, _ ->
        val mainItem = MainItemBinding.bind(itemView)
        mainItem.chip.text = user.name
      }
    )

    val item = helloItem()
    val item2 = morningItem()
    mergeAdapter.addAdapters(item, item2)

    mergeAdapter.addAdapter(
      Item(R.layout.main_item) {
        val mainItem = MainItemBinding.bind(itemView)
        mainItem.chip.text = "SWAP"
        mainItem.root.setOnClickListener {
          mergeAdapter.swap(item, item2)
        }
      }
    )

    binding.recycler.adapter = mergeAdapter
  }
}

data class User(
  val id: String,
  val name: String
)

private fun helloItem() =
  DataItem(
    data = User(id = "1", name = "hoge"),
    layoutId = R.layout.main_item
  ) { user, _ ->
    val mainItem = MainItemBinding.bind(itemView)
    mainItem.chip.text = user.name
  }

private fun morningItem() =
  DataItem(
    data = User(id = "2", name = "fuga"),
    layoutId = R.layout.main_item
  ) { user, _ ->
    val mainItem = MainItemBinding.bind(itemView)
    mainItem.chip.text = user.name
  }
