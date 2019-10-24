package com.github.satoshun.example.inconsistency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.InconsistencyActBinding
import com.github.satoshun.example.databinding.NameItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem

class InconsistencyDetectActivity : AppCompatActivity() {
  private lateinit var binding: InconsistencyActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.inconsistency_act)
    binding.recycler.layoutManager = LinearLayoutManager(this)
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
        "2", "1", "3"
      )
    )
  }
}

class DetectAdapter : GroupAdapter<GroupieViewHolder>() {
  fun updateNames(names: List<String>) {
    update(names.map { NameItem(it) })
  }
}

data class NameItem(
  private val name: String
) : BindableItem<NameItemBinding>(name.hashCode().toLong()) {
  override fun getLayout(): Int = R.layout.name_item

  override fun bind(binding: NameItemBinding, position: Int) {
    binding.title.text = name
  }
}
