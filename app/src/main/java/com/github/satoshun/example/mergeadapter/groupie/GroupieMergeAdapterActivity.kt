package com.github.satoshun.example.mergeadapter.groupie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.MainActBinding
import com.github.satoshun.example.databinding.MainItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class GroupieMergeAdapterActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val manager = LinearLayoutManager(this)
    binding.recycler.layoutManager = manager
  }
}

class GroupAdatepr1 : GroupAdapter<GroupieViewHolder>() {
  init {
    add(ChipItem("test"))
    add(ChipItem("test1"))
  }
}

class GroupAdatepr2 : GroupAdapter<GroupieViewHolder>() {
  init {
    add(ChipItem("hoge"))
    add(ChipItem("hoge2"))
  }
}

class ChipItem(
  private val title: String
) : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(holder: GroupieViewHolder, position: Int) {
    val binding = MainItemBinding.bind(holder.itemView)
    binding.chip.text = title
  }
}
