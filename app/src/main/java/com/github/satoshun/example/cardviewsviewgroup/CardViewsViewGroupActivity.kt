package com.github.satoshun.example.cardviewsviewgroup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.databinding.CardViewsViewGroupActBinding

class CardViewsViewGroupActivity : AppCompatActivity() {
  private lateinit var binding: CardViewsViewGroupActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = CardViewsViewGroupActBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}
