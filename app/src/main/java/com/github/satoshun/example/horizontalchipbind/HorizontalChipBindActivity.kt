package com.github.satoshun.example.horizontalchipbind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.databinding.ChipBinding
import com.github.satoshun.example.databinding.HorizontalChipBindlActBinding

class HorizontalChipBindActivity : AppCompatActivity() {
  private lateinit var binding: HorizontalChipBindlActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = HorizontalChipBindlActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // ok
    with(binding.recycler) {
      layoutManager = LinearLayoutManager(this@HorizontalChipBindActivity, RecyclerView.HORIZONTAL, false)
      adapter = SampleAdapter().apply {
        submitList((0..1000).map { it.toString() })
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
    val binding = ChipBinding.inflate(
      LayoutInflater.from(parent.context)
    )
    return object : RecyclerView.ViewHolder(binding.root) {
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val binding = ChipBinding.bind(holder.itemView)
    binding.chip.text = position.toString()
  }
}
