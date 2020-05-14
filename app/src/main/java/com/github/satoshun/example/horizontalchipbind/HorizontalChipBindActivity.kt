package com.github.satoshun.example.horizontalchipbind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.ChipBinding
import com.github.satoshun.example.databinding.HorizontalChipBindActBinding
import com.google.android.material.chip.Chip
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class HorizontalChipBindActivity : AppCompatActivity() {
  private lateinit var binding: HorizontalChipBindActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = HorizontalChipBindActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // ok
    with(binding.recycler) {
      layoutManager = LinearLayoutManager(this@HorizontalChipBindActivity, RecyclerView.HORIZONTAL, false)
      adapter = SampleAdapter().apply {
        submitList((0..1000).map { it.toString() })
      }
    }

    // ok
    with(binding.recycler2) {
      layoutManager = StaggeredGridLayoutManager(
        2,
        StaggeredGridLayoutManager.HORIZONTAL
      )
      adapter = SampleAdapter().apply {
        submitList((0..1000).map { it.toString() })
      }
    }

    // ok
    with(binding.recycler3) {
      layoutManager = StaggeredGridLayoutManager(
        2,
        StaggeredGridLayoutManager.HORIZONTAL
      )
      adapter = SampleAdapter().apply {
        submitList((0..1000).map { it.toString() })
      }
    }

    // ok
    with(binding.recycler4) {
      layoutManager = StaggeredGridLayoutManager(
        2,
        StaggeredGridLayoutManager.HORIZONTAL
      )
      adapter = SampleAdapter2().apply {
        update((0..1000).map {
          SampleItem(it.toString())
        })
      }
    }

    // ok
    with(binding.recycler5) {
      val myAdapter = SampleAdapter2().apply {
        update((0..1000).map {
          SampleItem(it.toString())
        })
      }
      adapter = myAdapter
      layoutManager = StaggeredGridLayoutManager(
        2,
        StaggeredGridLayoutManager.HORIZONTAL
      )
      setPadding(
        20,
        20,
        20,
        20
      )
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

private class SampleAdapter2 : GroupAdapter<GroupieViewHolder>()

private class SampleItem(
  val item: String
) : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.chip

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    val chip = viewHolder.itemView as Chip
    chip.text = item
  }
}
