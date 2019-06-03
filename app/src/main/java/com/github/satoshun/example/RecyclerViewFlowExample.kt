package com.github.satoshun.example

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.recycler_act.*

class RecyclerViewFlowExample : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.recycler_act)

    repeat(100) {
      val chip = Chip(this)
      chip.text = it.toString()

      chip.id = View.generateViewId()

      motion.addView(chip)
      flow.addView(chip)
    }
  }
}
