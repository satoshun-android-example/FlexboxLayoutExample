package com.github.satoshun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.core.view.doOnLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_act4.*

class MainActivity4 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act4)

    Glide.with(this)
      .load("https://pbs.twimg.com/profile_images/1125348342676897792/14E24iP9_400x400.jpg")
      .into(left)

    Glide.with(this)
      .load("https://pbs.twimg.com/profile_images/1125348342676897792/14E24iP9_400x400.jpg")
      .into(right)

    root.doOnLayout {
      root.setTransitionListener(object : MotionLayout.TransitionListener {
        private var state: Int = 0

        override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
        }

        override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
        }

        override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
        }

        override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
          if (state == 0) {
            root.setTransition(R.id.scene5_icon_begin, R.id.scene5_icon_end)
            root.setTransitionDuration(800)
            root.transitionToEnd()
          }
//          if (state == 1) {
//            root.setTransition(R.id.scene5_icon_begin, R.id.scene5_icon_end)
//            root.setTransitionDuration(800)
//            root.transitionToEnd()
//          }
          state++
        }

        override fun allowsTransition(p0: MotionScene.Transition?): Boolean {
          return true
        }
      })

      root.transitionToEnd()
    }
  }
}
