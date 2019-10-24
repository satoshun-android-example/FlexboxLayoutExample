package com.github.satoshun.example.alreadyattached

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.AlreadyAttachedActBinding

class LayoutManagerAlreadyAttachedActivity : AppCompatActivity() {
  private lateinit var binding: AlreadyAttachedActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.already_attached_act)

    val layoutManager = LinearLayoutManager(this)

    binding.recycler1.layoutManager = layoutManager

    // release LayoutManager?
    // part1
    binding.recycler1.layoutManager = null

    binding.recycler2.layoutManager = layoutManager
  }
}
