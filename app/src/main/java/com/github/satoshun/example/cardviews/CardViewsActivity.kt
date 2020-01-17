package com.github.satoshun.example.cardviews

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.CardViewItemBinding
import com.github.satoshun.example.databinding.CardViewsActBinding
import com.github.satoshun.example.databinding.TextViewItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CardViewsActivity : AppCompatActivity() {
  private lateinit var binding: CardViewsActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = CardViewsActBinding.inflate(layoutInflater)
    setContentView(binding.root)

//    binding.recycler.isEnabled = false
//    binding.recycler.isClickable = false
//    binding.recycler.setForeground(ColorDrawable(ContextCompat.getColor(
//      this,
//      android.R.color.transparent)))

    var isTouchDisable = true
    lifecycleScope.launch {
      binding.recycler.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
          return isTouchDisable
        }
      })
      delay(2000)
      isTouchDisable = false
    }

    binding.recycler.layoutManager = LinearLayoutManager(this)
    binding.recycler.adapter = GroupAdapter<GroupieViewHolder>().apply {
      addAll(
        (0..100).map { CardViewItem() }
      )
    }
  }
}

class TextViewItem : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.text_view_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    val binding = TextViewItemBinding.bind(viewHolder.itemView)
  }
}

class CardViewItem : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.card_view_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    val binding = CardViewItemBinding.bind(viewHolder.itemView)

    binding.root.setOnClickListener {
      Toast.makeText(it.context, "clicked", Toast.LENGTH_SHORT).show()
    }
  }
}
