package com.github.satoshun.merger

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("FunctionName")
fun <T : Any> ListItem(
  layoutId: Int,
  areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
  bind: RecyclerView.ViewHolder.(data: T, position: Int) -> Unit
): ListItem<T> = ListItem(
  layoutId,
  MergerDiffCallback(areItemsTheSame),
  bind,
  null
)

@Suppress("FunctionName")
fun <T : Any> ListItem(
  initialData: List<T>,
  layoutId: Int,
  areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
  bind: RecyclerView.ViewHolder.(data: T, position: Int) -> Unit
): ListItem<T> = ListItem(layoutId, areItemsTheSame, bind)
  .apply { submitList(initialData) }

open class ListItem<T : Any>(
  @LayoutRes private val layoutId: Int,
  diffCallback: DiffUtil.ItemCallback<T>,
  private val bind: RecyclerView.ViewHolder.(data: T, position: Int) -> Unit,
  private val bindPayloads: (RecyclerView.ViewHolder.(data: T, position: Int, payloads: MutableList<Any>) -> Unit)?
) : ListAdapter<T, RecyclerView.ViewHolder>(diffCallback) {
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
