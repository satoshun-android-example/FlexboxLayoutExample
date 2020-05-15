package com.github.satoshun.example.constraintlayoutmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.databinding.ConstraintMatchConstraintsActBinding
import com.github.satoshun.example.databinding.TextBinding

class ConstraintMatchConstraintsActivity : AppCompatActivity() {
  private lateinit var binding: ConstraintMatchConstraintsActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ConstraintMatchConstraintsActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    listOf(
      binding.recycler0,
      binding.recycler1,
      binding.recycler2
    ).forEachIndexed { index, recycler ->
      with(recycler) {
        layoutManager = LinearLayoutManager(
          this@ConstraintMatchConstraintsActivity,
          RecyclerView.HORIZONTAL,
          false
        )
        adapter = SampleAdapter().apply {
          submitList((10..1000).map { "$index $it" })
        }
      }
    }
  }
}

private class SampleAdapter : ListAdapter<String, RecyclerView.ViewHolder>(
  object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
      return oldItem == newItem
    }
  }
) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val binding = TextBinding.inflate(
      LayoutInflater.from(parent.context)
    )
    return object : RecyclerView.ViewHolder(binding.root) {
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val binding = TextBinding.bind(holder.itemView)
    binding.title.text = position.toString()
    println(getItem(position))
  }
}
