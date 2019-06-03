package com.github.satoshun.example.flexbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.R
import com.google.android.material.chip.Chip

internal class FlexboxAdapter : ListAdapter<Item, MyViewHolder>(DIFF) {
  init {
    submitList(items)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): MyViewHolder {
    return MyViewHolder(
      LayoutInflater.from(parent.context).inflate(
        R.layout.flex_box_item,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(
    holder: MyViewHolder,
    position: Int
  ) {
    holder.itemView.findViewById<Chip>(R.id.chip).text = getItem(position).name
  }
}

internal class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

private val DIFF = object : DiffUtil.ItemCallback<Item>() {
  override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
    return oldItem == newItem
  }
}

private val items = (0..1000).map {
  Item((it + 1000).toString())
}
