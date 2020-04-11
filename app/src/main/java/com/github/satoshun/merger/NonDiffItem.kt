package com.github.satoshun.merger

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("FunctionName")
fun NonDiffItem(
  layoutId: Int,
  bind: RecyclerView.ViewHolder.(position: Int) -> Unit
): NonDiffItem = NonDiffItem(
  layoutId,
  bind,
  null
)

class NonDiffItem(
  @LayoutRes private val layoutId: Int,
  private val bind: RecyclerView.ViewHolder.(position: Int) -> Unit,
  private val bindPayloads: (RecyclerView.ViewHolder.(position: Int, payloads: MutableList<Any>) -> Unit)?
) : ListAdapter<Int, RecyclerView.ViewHolder>(MergerDiffCallback()) {
  init {
    submitList(listOf(layoutId))
  }

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

  override fun getItemViewType(position: Int): Int {
    return layoutId
  }
}
