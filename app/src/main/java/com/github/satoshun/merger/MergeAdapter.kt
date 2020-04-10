package com.github.satoshun.merger

import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView

internal typealias RV = RecyclerView.Adapter<RecyclerView.ViewHolder>

fun MergeAdapter.addAdapters(vararg adapters: RV) {
  adapters.forEach { addAdapter(it) }
}

fun MergeAdapter.swap(a: RV, b: RV) {
  val adapters = adapters
  val aIndex = adapters.indexOfFirst { it == a }
  val bIndex = adapters.indexOfFirst { it == b }
  if (aIndex == -1 || bIndex == -1) {
    return
  }

  removeAdapter(a)
  removeAdapter(b)
  if (aIndex > bIndex) {
    addAdapter(bIndex, a)
    addAdapter(aIndex, b)
  } else {
    addAdapter(aIndex, b)
    addAdapter(bIndex, a)
  }
}

fun MergeAdapter.update(vararg adapters: RV) {
  getAdapters().forEach { removeAdapter(it) }
  addAdapters(*adapters)
}
