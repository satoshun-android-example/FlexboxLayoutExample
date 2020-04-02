package com.github.satoshun.example.mergeadapter.groupie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.MainActBinding
import com.github.satoshun.example.databinding.MainItemBinding
import com.github.satoshun.example.databinding.RecyclerItemBinding
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

    val mergeAdapter = MergeAdapter()
    mergeAdapter.addAdapter(GroupAdapter1())
    mergeAdapter.addAdapter(HorizontalAdapter(GroupAdapter2()))
    binding.recycler.adapter = mergeAdapter
  }
}

class GroupAdapter1 : GroupAdapter<GroupieViewHolder>() {
  init {
    add(ChipItem("test"))
    add(ChipItem("test1"))
  }
}

class GroupAdapter2 : GroupAdapter<GroupieViewHolder>() {
  init {
    addAll((0..10).map {
      ChipItem("hoge $it")
    })
  }
}

class HorizontalAdapter(
  adapter: GroupAdapter<GroupieViewHolder>
) : GroupAdapter<GroupieViewHolder>() {
  init {
    add(HorizontalItem(adapter))
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

class HorizontalItem(
  private val adapter: GroupAdapter<GroupieViewHolder>
) : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.recycler_item

  override fun bind(holder: GroupieViewHolder, position: Int) {
    val binding = RecyclerItemBinding.bind(holder.itemView)
    binding.recycler.adapter = adapter
    binding.recycler.layoutManager =
      LinearLayoutManager(
        binding.root.context,
        RecyclerView.HORIZONTAL,
        false
      )
  }
}
