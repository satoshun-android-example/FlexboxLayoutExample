package com.github.satoshun.example.merger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
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
import com.github.satoshun.merger.update

class MergerActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val manager = LinearLayoutManager(this)
    binding.recycler.layoutManager = manager
    (binding.recycler.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false

    val mergeAdapter = MergeAdapter()

    fun shuffleTest() {
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
          mainItem.button.text = "Shuffle ListItem"
          mainItem.root.setOnClickListener {
            items.submitList(data.shuffled())
          }
        }
      )
    }
    shuffleTest()

    fun swapTest() {
      val item = Item(
        data = User(id = "1", name = "t1"),
        sameItemKey = { it.id },
        layoutId = R.layout.main_item
      ) { user, _ ->
        val mainItem = MainItemBinding.bind(itemView)
        mainItem.chip.text = user.name
      }

      val item2 = Item(
        data = User(id = "2", name = "t2"),
        sameItemKey = { it.id },
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
    }
    swapTest()

    fun updateTest() {
      val data1 = User(id = "1", name = "t1")
      val item1 = Item(
        data = data1,
        sameItemKey = { it.id },
        layoutId = R.layout.main_item
      ) { user, _ ->
        val mainItem = MainItemBinding.bind(itemView)
        mainItem.chip.text = user.name
      }

      val data2 = User(id = "2", name = "t2")
      val item2 = Item(
        data = data2,
        sameItemKey = { it.id },
        layoutId = R.layout.main_item
      ) { user, _ ->
        val mainItem = MainItemBinding.bind(itemView)
        mainItem.chip.text = user.name
      }

      mergeAdapter.addAdapters(item1, item2)

      mergeAdapter.addAdapter(
        NonDiffItem(R.layout.button_item) {
          val mainItem = ButtonItemBinding.bind(itemView)
          mainItem.button.text = "Update Item"
          mainItem.root.setOnClickListener {
            item1.update(
              if (item1.data == data1) data2
              else data1
            )
            item2.update(
              if (item2.data == data1) data2
              else data1
            )
          }
        }
      )
    }
    updateTest()

    binding.recycler.adapter = mergeAdapter
  }
}

data class User(
  val id: String,
  val name: String
)
