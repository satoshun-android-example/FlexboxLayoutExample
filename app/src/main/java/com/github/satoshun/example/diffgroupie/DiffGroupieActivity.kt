package com.github.satoshun.example.diffgroupie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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
    val adapter = DiffAdapter()
    binding.recycler.adapter = adapter

    lifecycleScope.launch {
      while (true) {
        delay(3000)
        adapter.update()
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
        BasicItem(),
        BasicIdItem(0.toLong()),
        BasicIdSameContentsItem(1.toLong())
      )
    )
  }
}

class BasicItem : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.basic_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    println("ID !=, equals != ")
  }
}

class BasicIdItem(id: Long) : Item<GroupieViewHolder>(id) {
  override fun getLayout(): Int = R.layout.basic_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    println("ID ==, equals !=")
  }
}

data class BasicIdSameContentsItem(private val _id: Long) : Item<GroupieViewHolder>(_id) {
  override fun getLayout(): Int = R.layout.basic_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    println("ID ==, equals ==")
  }
}
