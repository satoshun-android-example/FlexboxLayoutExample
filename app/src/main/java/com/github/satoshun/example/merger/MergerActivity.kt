package com.github.satoshun.example.merger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.ButtonItemBinding
import com.github.satoshun.example.databinding.MainActBinding
import com.github.satoshun.example.databinding.MainItemBinding
import com.github.satoshun.merger.Item
import com.github.satoshun.merger.ListItem
import com.github.satoshun.merger.NonDiffItem
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

    val data = listOf(
      User(id = "1", name = "sato"),
      User(id = "2", name = "shun"),
      User(id = "3", name = "desu")
    )
    val items = ListItem(
      initialData = data,
      layoutId = R.layout.main_item,
      sameItemKey = { it.id }
    ) { user, _ ->
      val mainItem = MainItemBinding.bind(itemView)
      mainItem.chip.text = user.name
    }

    mergeAdapter.addAdapter(items)

    mergeAdapter.addAdapter(
      NonDiffItem(R.layout.button_item) {
        val mainItem = ButtonItemBinding.bind(itemView)
        mainItem.button.text = "Sort Item"
        mainItem.root.setOnClickListener {
          items.submitList(data.shuffled())
        }
      }
    )

    val item = Item(
      data = User(id = "1", name = "t1"),
      layoutId = R.layout.main_item
    ) { user, _ ->
      val mainItem = MainItemBinding.bind(itemView)
      mainItem.chip.text = user.name
    }

    val item2 = Item(
      data = User(id = "2", name = "t2"),
      layoutId = R.layout.main_item
    ) { user, _ ->
      val mainItem = MainItemBinding.bind(itemView)
      mainItem.chip.text = user.name
    }

    mergeAdapter.addAdapters(item, item2)

    mergeAdapter.addAdapter(
      NonDiffItem(R.layout.button_item) {
        val mainItem = ButtonItemBinding.bind(itemView)
        mainItem.button.text = "Swap Adapter"
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
