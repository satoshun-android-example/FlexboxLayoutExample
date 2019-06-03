package com.github.satoshun.example.flexbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.R
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.flex_box_act.*

class RecyclerViewFlexboxActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.flex_box_act)

    recycler.layoutManager = FlexboxLayoutManager(this).apply {
      flexDirection = FlexDirection.COLUMN
    }
    recycler.adapter = FlexboxAdapter()
  }
}

data class Item(
  val name: String
)
