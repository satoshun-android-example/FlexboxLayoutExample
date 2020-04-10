package com.github.satoshun.merger

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

internal class MergerDiffCallback<T : Any>(
  private val areItemsTheSame: (T, T) -> Boolean
) : DiffUtil.ItemCallback<T>() {
  override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
    return areItemsTheSame.invoke(oldItem, newItem)
  }

  @SuppressLint("DiffUtilEquals")
  override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
    return oldItem == newItem
  }
}
