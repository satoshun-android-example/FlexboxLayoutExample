package com.github.satoshun.example.cardviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.CardViewItemBinding
import com.github.satoshun.example.databinding.CardViewsActBinding
import com.github.satoshun.example.databinding.DiffActBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CardViewsActivity : AppCompatActivity() {
  private lateinit var binding: CardViewsActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = CardViewsActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.recycler.layoutManager = LinearLayoutManager(this)
    binding.recycler.adapter = GroupAdapter<GroupieViewHolder>().apply {
      addAll(
        (0..100).map { CardViewItem() }
      )
    }
  }
}

class CardViewItem : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.card_view_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    val binding = CardViewItemBinding.bind(viewHolder.itemView)
  }
}
