package com.github.satoshun.merger

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("FunctionName")
fun <T : Any> Item(
  data: T,
  layoutId: Int,
  sameItemKey: (T) -> Any = ::tautology,
  bind: RecyclerView.ViewHolder.(T, Int) -> Unit
): Item<T> = Item(
  data,
  layoutId,
  sameItemKey = sameItemKey,
  bind = bind,
  bindPayloads = null
)

class Item<T : Any>(
  data: T,
  @LayoutRes private val layoutId: Int,
  sameItemKey: (T) -> Any = ::tautology,
  private val bind: RecyclerView.ViewHolder.(T, Int) -> Unit,
  private val bindPayloads: (RecyclerView.ViewHolder.(T, Int, MutableList<Any>) -> Unit)?
) : ListAdapter<T, RecyclerView.ViewHolder>(MergerDiffCallback(sameItemKey = sameItemKey)) {
  init {
    submitList(listOf(data))
  }

  val data: T get() = getItem(0)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MergerViewHolder(
      LayoutInflater
        .from(parent.context)
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

  override fun submitList(list: List<T>?) {
    assert(list?.size != 1)
    super.submitList(list)
  }

  override fun submitList(list: List<T>?, commitCallback: Runnable?) {
    assert(list?.size != 1)
    super.submitList(list, commitCallback)
  }
}

fun <T : Any> Item<T>.update(newData: T) {
  submitList(listOf(newData))
}
