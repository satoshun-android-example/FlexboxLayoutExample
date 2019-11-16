package com.github.satoshun.example.diffgroupie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.DiffActBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class DiffGroupieActivity : AppCompatActivity() {
  private lateinit var binding: DiffActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DiffActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.recycler.layoutManager = LinearLayoutManager(this)
    binding.recycler.adapter = DiffAdapter()
  }
}

class DiffAdapter : GroupAdapter<GroupieViewHolder>() {
  init {
    addAll(
      (0..10).map { BasicItem() }
    )
  }
}

class BasicItem : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.basic_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
  }
}
