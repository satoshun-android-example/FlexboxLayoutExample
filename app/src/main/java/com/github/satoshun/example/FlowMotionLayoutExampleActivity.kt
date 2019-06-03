package com.github.satoshun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.flow_motion_layout_act.*

class FlowMotionLayoutExampleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.flow_motion_layout_act)

    var isTransitionStart = true
    transition_start.setOnClickListener {
      if (isTransitionStart) {
        root.transitionToEnd()
      } else {
        root.transitionToStart()
      }
      isTransitionStart = !isTransitionStart
    }
  }
}
