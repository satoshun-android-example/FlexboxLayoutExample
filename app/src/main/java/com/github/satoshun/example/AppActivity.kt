package com.github.satoshun.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.flexbox.RecyclerViewFlexboxActivity
import kotlinx.android.synthetic.main.app_act.*

class AppActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.app_act)

    main1.setOnClickListener {
      startActivity(Intent(this, MainActivity::class.java))
    }
    main2.setOnClickListener {
      startActivity(Intent(this, MainActivity2::class.java))
    }
    main3.setOnClickListener {
      startActivity(Intent(this, MainActivity3::class.java))
    }
    main4.setOnClickListener {
      startActivity(Intent(this, MainActivity4::class.java))
    }
    flow.setOnClickListener {
      startActivity(Intent(this, FlowExampleActivity::class.java))
    }
    flow_motion_layout.setOnClickListener {
      startActivity(Intent(this, FlowMotionLayoutExampleActivity::class.java))
    }
    stack_card.setOnClickListener {
      startActivity(Intent(this, StackCardExampleActivity::class.java))
    }
    recycler.setOnClickListener {
      startActivity(Intent(this, RecyclerViewFlowExample::class.java))
    }
    flexbox.setOnClickListener {
      startActivity(Intent(this, RecyclerViewFlexboxActivity::class.java))
    }
  }
}
