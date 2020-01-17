package com.github.satoshun.example.itemtouchhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.databinding.ItemTouchHelperActBinding

class ItemTouchHelperActivity : AppCompatActivity() {
  private lateinit var binding: ItemTouchHelperActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ItemTouchHelperActBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}
