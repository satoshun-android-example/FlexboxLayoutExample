package com.github.satoshun.example.flexbox2

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.*
import com.google.android.flexbox.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem

class FlexboxActivity2 : AppCompatActivity() {
  private lateinit var binding: Flexbox2ActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.flexbox2_act)

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
