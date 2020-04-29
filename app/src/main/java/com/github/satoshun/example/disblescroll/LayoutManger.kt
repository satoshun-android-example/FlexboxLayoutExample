package com.github.satoshun.example.disblescroll

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager

// canScrollVertically is false
class LayoutManger1(context: Context) : LinearLayoutManager(context) {
  override fun canScrollVertically(): Boolean {
    // many call
    Log.d("canScrollVertically", "${System.currentTimeMillis()}")
    return false
  }

  override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
    // never call
    Log.d("scrollVerticallyBy", "$dy")
    return super.scrollVerticallyBy(dy, recycler, state)
  }

  override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
    // never call
    Log.d("scrollHorizontallyBy", "$dx")
    return super.scrollHorizontallyBy(dx, recycler, state)
  }
}

// scrollHorizontallyBy isn't implemented
class LayoutManger2(context: Context) : LinearLayoutManager(context) {
  override fun canScrollVertically(): Boolean {
    // many call
    Log.d("canScrollVertically", "${System.currentTimeMillis()}")
    return true
  }

  override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
    // many call
    Log.d("scrollVerticallyBy", "$dy")
    return 0
  }

  override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
    // never call
    Log.d("scrollHorizontallyBy", "$dx")
    return super.scrollHorizontallyBy(dx, recycler, state)
  }
}

// scrollHorizontallyBy isn't implemented
class LayoutManger3(context: Context) : FlexboxLayoutManager(context) {
  override fun canScrollVertically(): Boolean {
    // many call
    Log.d("canScrollVertically", "${System.currentTimeMillis()}")
    return false
  }

  override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
    // never call
    Log.d("scrollVerticallyBy", "$dy")
    return super.scrollVerticallyBy(dy, recycler, state)
  }

  override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
    // never call
    Log.d("scrollHorizontallyBy", "$dx")
    return super.scrollHorizontallyBy(dx, recycler, state)
  }
}

typealias LayoutManger4 = FlexboxLayoutManager
