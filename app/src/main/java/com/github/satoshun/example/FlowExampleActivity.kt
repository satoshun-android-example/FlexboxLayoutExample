package com.github.satoshun.example

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.flow_act.*

class FlowExampleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.flow_act)

    var mode = Flow.WRAP_CHAIN
    var style = Flow.CHAIN_SPREAD
    var isFull = true

    change_wrap_mode.setOnClickListener {
      mode = (mode + 1) % 3
      flow.setWrapMode(mode)
      flow.requestLayout()

      changeStatus(mode, style, isFull)
    }

    change_style.setOnClickListener {
      style = (style + 1) % 3
      flow.setHorizontalStyle(style)
      flow.requestLayout()

      changeStatus(mode, style, isFull)
    }

    change_width.setOnClickListener {
      if (isFull) {
        flow.layoutParams = flow.layoutParams.apply {
          width = 400
        }
      } else {
        flow.layoutParams = flow.layoutParams.apply {
          width = ConstraintLayout.LayoutParams.MATCH_PARENT
        }
      }
      isFull = !isFull

      changeStatus(mode, style, isFull)
    }

    changeStatus(mode, style, isFull)
  }

  @SuppressLint("SetTextI18n")
  private fun changeStatus(mode: Int, style: Int, isFull: Boolean) {
    val m = when (mode) {
      Flow.WRAP_NONE -> "none"
      Flow.WRAP_CHAIN -> "chain"
      else -> "aligned"
    }
    val s = when (style) {
      Flow.CHAIN_SPREAD -> "spread"
      Flow.CHAIN_SPREAD_INSIDE -> "spread_inside"
      else -> "packed"
    }
    val w = if (isFull) "full" else "not full"

    status.text = "wrap mode $m, style: $s, width: $w"
  }
}
