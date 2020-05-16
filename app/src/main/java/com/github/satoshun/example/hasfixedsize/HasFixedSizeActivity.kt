package com.github.satoshun.example.hasfixedsize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.databinding.HasFixedSizeActBinding
import com.github.satoshun.example.databinding.TextBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HasFixedSizeActivity : AppCompatActivity() {
  private lateinit var binding: HasFixedSizeActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = HasFixedSizeActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    with(binding.recycler0) {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(
        this@HasFixedSizeActivity,
        LinearLayoutManager.HORIZONTAL,
        false
      )
      val myAdapter = SampleAdapter()
      adapter = myAdapter

      lifecycleScope.launch {
        delay(3000)
        myAdapter.submitList((10..1000).map { toString() })
      }
    }

    with(binding.recycler1) {
      layoutManager = LinearLayoutManager(
        this@HasFixedSizeActivity,
        LinearLayoutManager.HORIZONTAL,
        false
      )
      val myAdapter = SampleAdapter()
      adapter = myAdapter

      lifecycleScope.launch {
        delay(10000)
        myAdapter.submitList((10..1000).map { toString() })
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
