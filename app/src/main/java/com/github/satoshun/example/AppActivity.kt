package com.github.satoshun.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.alreadyattached.LayoutManagerAlreadyAttachedActivity
import com.github.satoshun.example.flexbox.FlexboxActivity
import com.github.satoshun.example.flexbox2.FlexboxActivity2
import com.github.satoshun.example.inconsistency.InconsistencyDetectActivity
import kotlinx.android.synthetic.main.app_act.*

class AppActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.app_act)

    main1.setOnClickListener {
      startActivity(Intent(this, FlexboxActivity::class.java))
    }

    flexbox2.setOnClickListener {
      startActivity(Intent(this, FlexboxActivity2::class.java))
    }

    main2.setOnClickListener {
      startActivity(Intent(this, StaggeredActivity::class.java))
    }

    inconsistency.setOnClickListener {
      startActivity(Intent(this, InconsistencyDetectActivity::class.java))
    }
  }
}
