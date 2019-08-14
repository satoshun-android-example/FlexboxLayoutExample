package com.github.satoshun.example

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.databinding.MainActBinding
import com.github.satoshun.example.databinding.MainContainerItemBinding
import com.github.satoshun.example.databinding.MainItemBinding
import com.github.satoshun.example.databinding.SubContainerItemBinding
import com.google.android.flexbox.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem

class FlexboxActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.main_act)

    val manager = LinearLayoutManager(this)
    binding.recycler.layoutManager = manager

    binding.recycler.adapter = MainAdapter(flexboxLayoutManager(
      context = this,
      flexDirection = FlexDirection.ROW,
      flexWrap = FlexWrap.WRAP
    ))
  }
}

class MainAdapter(manager: FlexboxLayoutManager) : GroupAdapter<ViewHolder>() {
  init {
    addAll(
      listOf(
        MainContainerItem(
          manager,
          ChipMainAdapter().apply {
            updateChips(mockItems1)
          }),
        SubContainerItem(
          manager,
          ChipSubAdapter().apply {
            updateChips(mockItems)
          })
      )
    )
  }
}

class ChipMainAdapter : GroupAdapter<ViewHolder>() {
  fun updateChips(items: List<String>) {
    addAll(items.map { MainItem(it) })
  }
}

class MainContainerItem(
  private val layoutManager: FlexboxLayoutManager,
  private val adapter: ChipMainAdapter
) : BindableItem<MainContainerItemBinding>() {
  override fun getLayout(): Int = R.layout.main_container_item

  override fun bind(binding: MainContainerItemBinding, position: Int) {
    if (binding.recycler.adapter == null) {
      binding.recycler.layoutManager = layoutManager
      binding.recycler.adapter = adapter

      binding.recycler.viewTreeObserver.addOnGlobalLayoutListener(
        OnViewGlobalLayoutListener(binding.recycler)
      )
    }
  }
}

class ChipSubAdapter : GroupAdapter<ViewHolder>() {
  fun updateChips(items: List<String>) {
    addAll(items.map { MainItem(it) })
  }
}

class SubContainerItem(
  private val mainManager: FlexboxLayoutManager,
  private val subAdapter: ChipSubAdapter
) : BindableItem<SubContainerItemBinding>() {
  override fun getLayout(): Int = R.layout.sub_container_item

  override fun bind(binding: SubContainerItemBinding, position: Int) {
    if (binding.recycler.adapter == null) {
      binding.recycler.layoutManager = flexboxLayoutManager(
        context = binding.root.context,
        flexDirection = FlexDirection.COLUMN,
        justifyContent = JustifyContent.FLEX_START,
        alignItems = AlignItems.FLEX_START
      )
      binding.recycler.adapter = subAdapter

      binding.recycler.viewTreeObserver.addOnGlobalLayoutListener(
        OnViewGlobalLayoutListener2(binding.recycler, mainManager)
      )
    }
  }
}


class MainItem(
  private val title: String
) : BindableItem<MainItemBinding>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(binding: MainItemBinding, position: Int) {
    binding.chip.text = title
  }
}

val mockItems1 = (100..180).map {
  it.toString()
}
private val mockItems = (200..250).map {
  it.toString()
}

private class OnViewGlobalLayoutListener(
  private val view: View
) : ViewTreeObserver.OnGlobalLayoutListener {
  companion object {
    private const val maxHeightPx = 130 * 5
  }

  override fun onGlobalLayout() {
    if (view.height > maxHeightPx) {
      view.layoutParams = view.layoutParams.apply { height = maxHeightPx }
    }
  }
}

private class OnViewGlobalLayoutListener2(
  private val view: View,
  private val manager: FlexboxLayoutManager
) : ViewTreeObserver.OnGlobalLayoutListener {

  override fun onGlobalLayout() {
    if (manager.flexLines.size >= 5) {
      view.layoutParams = view.layoutParams.apply { height = 0 }
    } else {
      // limit
      view.layoutParams = view.layoutParams.apply { height = 130 * (5 - manager.flexLines.size) }
    }
  }
}

private fun flexboxLayoutManager(
  context: Context,
  @FlexDirection flexDirection: Int = FlexDirection.ROW,
  @FlexWrap flexWrap: Int = FlexWrap.WRAP,
  @JustifyContent justifyContent: Int = JustifyContent.FLEX_START,
  @AlignItems alignItems: Int = AlignItems.STRETCH
) = FlexboxLayoutManager(context, flexDirection, flexWrap)
  .apply {
    this.justifyContent = justifyContent
    this.alignItems = alignItems
  }
