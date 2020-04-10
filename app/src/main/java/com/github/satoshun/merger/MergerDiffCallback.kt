package com.github.satoshun.merger

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

internal class MergerDiffCallback<T : Any>(
  private val areItemsTheSame: (T, T) -> Boolean = ::equals,
  private val areContentsTheSame: (T, T) -> Boolean = ::equals
) : DiffUtil.ItemCallback<T>() {
  override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
    return areItemsTheSame.invoke(oldItem, newItem)
  }

  @SuppressLint("DiffUtilEquals")
  override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
    return areContentsTheSame.invoke(oldItem, newItem)
  }
}

internal fun <T> equals(a: T, b: T): Boolean =
  a == b

internal fun <T> negate(a: T, b: T): Boolean = false
