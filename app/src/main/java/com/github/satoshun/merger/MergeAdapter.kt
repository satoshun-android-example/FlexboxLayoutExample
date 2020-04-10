package com.github.satoshun.merger

import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView

fun MergeAdapter.addAdapters(vararg adapters: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
  adapters.forEach { addAdapter(it) }
}

fun MergeAdapter.update(vararg adapters: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
  getAdapters().forEach { removeAdapter(it) }
  addAdapters(*adapters)
}
