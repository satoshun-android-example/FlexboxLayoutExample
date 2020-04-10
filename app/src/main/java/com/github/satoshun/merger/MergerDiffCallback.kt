package com.github.satoshun.merger

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

internal class MergerDiffCallback<T : Any>(
  private val sameItemKey: (T) -> Any = ::tautology,
  private val sameContentKey: (T) -> Any = ::tautology
) : DiffUtil.ItemCallback<T>() {
  override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
    return sameItemKey(oldItem) == sameItemKey(newItem)
  }

  @SuppressLint("DiffUtilEquals")
  override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
    return sameContentKey(oldItem) == sameContentKey(newItem)
  }
}

internal fun <T : Any> tautology(a: T): Any = a

internal fun <T : Any> equals(a: T, b: T): Boolean =
  a == b

internal fun <T : Any> negate(a: T, b: T): Boolean = false
