package com.github.satoshun.merger

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("FunctionName")
fun <T : Any> DataItem(
  data: T,
  layoutId: Int,
  bind: RecyclerView.ViewHolder.(T, Int) -> Unit
): DataItem<T> = DataItem(
  data,
  layoutId,
  bind,
  null
)

class DataItem<T : Any>(
  private val data: T,
  @LayoutRes private val layoutId: Int,
  private val bind: RecyclerView.ViewHolder.(T, Int) -> Unit,
  private val bindPayloads: (RecyclerView.ViewHolder.(T, Int, MutableList<Any>) -> Unit)?
) : ListAdapter<T, RecyclerView.ViewHolder>(MergerDiffCallback()) {
  init {
    submitList(listOf(data))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MergerViewHolder(
      LayoutInflater.from(parent.context)
        .inflate(layoutId, parent, false)
    )
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    holder.bind(getItem(position), position)
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
      holder.bindPayloads(getItem(position), position, payloads)
    }
  }

  override fun getItemViewType(position: Int): Int {
    return layoutId
  }
}
