package com.github.satoshun.example.alreadyattached

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.databinding.AlreadyAttachedActBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class LayoutManagerAlreadyAttachedActivity : AppCompatActivity() {
  private lateinit var binding: AlreadyAttachedActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = AlreadyAttachedActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    run {
      val layoutManager = LinearLayoutManager(this)

      binding.recycler1.layoutManager = layoutManager

      // release LayoutManager?
      // part1
      binding.recycler1.layoutManager = null

      binding.recycler2.layoutManager = layoutManager
    }

    // set same adapter
    run {
      binding.recycler3.layoutManager = LinearLayoutManager(this)
      val adapter = GroupAdapter<GroupieViewHolder>()
      binding.recycler3.adapter = adapter

      binding.recycler4.layoutManager = LinearLayoutManager(this)
      binding.recycler4.adapter = adapter
    }
  }
}
