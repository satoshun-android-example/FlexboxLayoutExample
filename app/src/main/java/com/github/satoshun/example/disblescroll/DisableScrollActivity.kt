package com.github.satoshun.example.disblescroll

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.databinding.DisableScrollActBinding

class DisableScrollActivity : AppCompatActivity() {
  private lateinit var binding: DisableScrollActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DisableScrollActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    with(binding.recycler) {
      layoutManager = LayoutManger1(this@DisableScrollActivity)
      layoutManager = LayoutManger2(this@DisableScrollActivity)
      layoutManager = LayoutManger3(this@DisableScrollActivity)
      layoutManager = LayoutManger4(this@DisableScrollActivity)
      layoutManager = LinearLayoutManager(this@DisableScrollActivity)

      isNestedScrollingEnabled = false

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
    val textView = TextView(parent.context)
    return object : RecyclerView.ViewHolder(textView) {
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val view = holder.itemView as TextView
    view.text = getItem(position)

    Log.d("onBind", "$position")
  }
}
