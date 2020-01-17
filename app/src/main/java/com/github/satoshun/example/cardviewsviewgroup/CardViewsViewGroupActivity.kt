package com.github.satoshun.example.cardviewsviewgroup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.databinding.CardViewsActBinding
import com.github.satoshun.example.databinding.CardViewsViewGroupActBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class CardViewsViewGroupActivity : AppCompatActivity() {
  private lateinit var binding: CardViewsViewGroupActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = CardViewsViewGroupActBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}
