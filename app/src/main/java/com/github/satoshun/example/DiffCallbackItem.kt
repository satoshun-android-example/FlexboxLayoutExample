package com.github.satoshun.example

import androidx.recyclerview.widget.DiffUtil
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

abstract class DiffCallbackItem<T, VH : GroupieViewHolder>(
  private val data: T,
  private val callback: DiffUtil.ItemCallback<T>
) : Item<VH>() {
  override fun isSameAs(other: Item<*>): Boolean {
    if (viewType != other.viewType) {
      return false
    }
    val otherData = other.getOtherData() ?: return false
    return callback.areItemsTheSame(otherData, data)
  }

  override fun hasSameContentAs(other: Item<*>): Boolean {
    val otherData = other.getOtherData() ?: return false
    return callback.areContentsTheSame(otherData, data)
  }

  override fun getChangePayload(newItem: Item<*>?): Any? {
    val newData = newItem?.getOtherData() ?: return null
    return callback.getChangePayload(data, newData)
  }

  private fun Item<*>.getOtherData(): T? {
    val other = this
    if (other !is DiffCallbackItem<*, *>) return null
    @Suppress("UNCHECKED_CAST")
    return other.data as? T
  }
}
