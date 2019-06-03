package com.github.satoshun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.databinding.MainActBinding
import com.github.satoshun.example.databinding.MainContainerItemBinding
import com.github.satoshun.example.databinding.MainItemBinding
import com.google.android.flexbox.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import android.opengl.ETC1.getHeight
import android.view.View
import android.view.ViewTreeObserver


class MainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.main_act)

    val manager = LinearLayoutManager(this)
    binding.recycler.layoutManager = manager
    binding.recycler.adapter = MainAdapter()
  }
}

class MainAdapter : GroupAdapter<ViewHolder>() {
  init {
    addAll(
      listOf(
        MainContainerItem(ChipAdapter().apply {
          updateChips(mockItems)
        })
      )
    )
  }
}

class ChipAdapter : GroupAdapter<ViewHolder>() {
  fun updateChips(items: List<String>) {
    addAll(items.map { MainItem(it) })
  }
}

class MainContainerItem(
  private val adapter: ChipAdapter
) : BindableItem<MainContainerItemBinding>() {
  override fun getLayout(): Int = R.layout.main_container_item

  override fun bind(binding: MainContainerItemBinding, position: Int) {
    if (binding.recycler.adapter == null) {
      binding.recycler.layoutManager = FlexboxLayoutManager(binding.root.context).apply {
        flexDirection = FlexDirection.ROW
        flexWrap = FlexWrap.WRAP
      }
      binding.recycler.adapter = adapter

      binding.recycler.viewTreeObserver.addOnGlobalLayoutListener(
        OnViewGlobalLayoutListener(binding.recycler)
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

private val mockItems = (100..180).map {
  it.toString()
}

private class OnViewGlobalLayoutListener(
  private val view: View
) : ViewTreeObserver.OnGlobalLayoutListener {
  companion object {
    private const val maxHeightPx = 620
  }

  override fun onGlobalLayout() {
    if (view.height > maxHeightPx) {
      view.layoutParams = view.layoutParams.apply { height = maxHeightPx }
    }
  }
}
