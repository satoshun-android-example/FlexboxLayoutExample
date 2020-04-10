package com.github.satoshun.merger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class Item(
  @LayoutRes private val layoutId: Int,
  private val bind: RecyclerView.ViewHolder.(position: Int) -> Unit,
  private val bindPayloads: (RecyclerView.ViewHolder.(position: Int, payloads: MutableList<Any>) -> Unit)?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MergerViewHolder(
      LayoutInflater
        .from(parent.context)
        .inflate(layoutId, parent, false)
    )
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    holder.bind(position)
  }

  override fun onBindViewHolder(
    holder: RecyclerView.ViewHolder,
    position: Int,
    payloads: MutableList<Any>
  ) {
    val bindPayloads = bindPayloads
    if (bindPayloads == null) {
      super.onBindViewHolder(holder, position, payloads)
    } else {
      holder.bindPayloads(position, payloads)
    }
  }

  override fun getItemCount(): Int = 1
}

class MergerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
