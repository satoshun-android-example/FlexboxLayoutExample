package com.github.satoshun.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.flexbox.FlexboxActivity
import kotlinx.android.synthetic.main.app_act.*

class AppActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.app_act)

    main1.setOnClickListener {
      startActivity(Intent(this, FlexboxActivity::class.java))
    }

    main2.setOnClickListener {
      startActivity(Intent(this, StaggeredActivity::class.java))
    }
  }
}
