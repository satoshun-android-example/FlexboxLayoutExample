package com.github.satoshun.example.inconsistency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.postDelayed
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.InconsistencyActBinding
import com.github.satoshun.example.databinding.NameItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class InconsistencyDetectActivity : AppCompatActivity() {
  private lateinit var binding: InconsistencyActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = InconsistencyActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.recycler.layoutManager = StaggeredGridLayoutManager(
      2,
      StaggeredGridLayoutManager.HORIZONTAL
    )
    val adapter = DetectAdapter()
    binding.recycler.adapter = adapter

    adapter.updateNames(
      listOf(
        "1", "2", "3",
        "1", "2", "3",
        "1", "2", "3"
      )
    )

    adapter.updateNames(
      listOf(
        "2", "1", "3",
        "2", "1", "3",
        "2", "1", "3",
        "2", "1", "3",
        "4"
      )
    )

    binding.root.postDelayed(1) {
      adapter.updateNames(
        listOf(
          "1", "2", "3",
          "1", "2", "3",
          "1", "2", "3"
        )
      )
    }
  }
}

class DetectAdapter : GroupAdapter<GroupieViewHolder>() {
  fun updateNames(names: List<String>) {
    update(names.map { NameItem(it) })
  }
}

data class NameItem(
  private val name: String
) : Item<GroupieViewHolder>(name.hashCode().toLong()) {
  override fun getLayout(): Int = R.layout.name_item

  override fun bind(holder: GroupieViewHolder, position: Int) {
    val binding = NameItemBinding.bind(holder.itemView)
    binding.title.text = name
  }
}
