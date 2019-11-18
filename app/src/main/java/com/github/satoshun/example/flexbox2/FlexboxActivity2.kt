package com.github.satoshun.example.flexbox2

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.Flexbox2ActBinding
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.chip.Chip
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class FlexboxActivity2 : AppCompatActivity() {
  private lateinit var binding: Flexbox2ActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = Flexbox2ActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val linear = FlexLinearLayoutManager(this)
    val flexbox = flexboxLayoutManager(
      context = this,
      flexDirection = FlexDirection.ROW,
      justifyContent = JustifyContent.FLEX_START,
      alignItems = AlignItems.FLEX_START
    )

    binding.recycler.layoutManager = flexbox
    binding.recycler.adapter = MainAdapter()

    binding.floating.setOnClickListener {
      if (binding.recycler.layoutManager == linear) {
        binding.recycler.layoutManager = flexbox
      } else {
        binding.recycler.layoutManager = linear
      }
    }
  }
}

class FlexLinearLayoutManager(
  context: Context
) : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {
  override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
    FlexboxLayoutManager.LayoutParams(
      ViewGroup.LayoutParams.WRAP_CONTENT,
      ViewGroup.LayoutParams.WRAP_CONTENT
    )

  override fun generateLayoutParams(c: Context, attrs: AttributeSet): RecyclerView.LayoutParams =
    FlexboxLayoutManager.LayoutParams(c, attrs)
}

class MainAdapter : GroupAdapter<GroupieViewHolder>() {
  init {
    addAll(mockItems1.map { MainItem(it) })
  }
}

class MainItem(
  private val title: String
) : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(holder: GroupieViewHolder, position: Int) {
    holder.itemView.findViewById<Chip>(R.id.chip).text = title
  }
}

val mockItems1 = (100..300).map {
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
