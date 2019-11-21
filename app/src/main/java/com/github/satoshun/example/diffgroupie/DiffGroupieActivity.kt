package com.github.satoshun.example.diffgroupie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.DiffActBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DiffGroupieActivity : AppCompatActivity() {
  private lateinit var binding: DiffActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DiffActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.recycler.layoutManager = LinearLayoutManager(this)
    binding.recycler.itemAnimator = (binding.recycler.itemAnimator as SimpleItemAnimator)
//      .apply {
//        supportsChangeAnimations = false
//      }
    val adapter = DiffAdapter()
    binding.recycler.adapter = adapter

    lifecycleScope.launch {
      while (true) {
        delay(1500)
//        adapter.update()
        adapter.changedPayloads()
      }
    }
  }
}

class DiffAdapter : GroupAdapter<GroupieViewHolder>() {
  init {
    update()
  }

  fun update() {
    update(
      listOf(
//        BasicIdItem(3)
        BasicIdItem(3.toLong())
//        BasicIdSameContentsItem(4.toLong())
      )
    )
  }

  fun changedPayloads() {
//    getItem(0).notifyChanged()
    update()
  }
}

class BasicItem : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.basic_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    println("${viewHolder.itemView.hashCode()}: ID !=, equals != ")
  }
}

class BasicIdItem(id: Long) : Item<GroupieViewHolder>(id) {
  override fun getLayout(): Int = R.layout.basic_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    println("${viewHolder.itemView.hashCode()}: ID ==, equals !=")
  }

  override fun getChangePayload(newItem: Item<*>): Any? {
    return newItem.id
  }
}

data class BasicIdSameContentsItem(private val _id: Long) : Item<GroupieViewHolder>(_id) {
  override fun getLayout(): Int = R.layout.basic_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    println("${viewHolder.itemView.hashCode()}: ID ==, equals ==")
  }
}
