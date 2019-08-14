package com.github.satoshun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.satoshun.example.databinding.StaggeredActBinding

class StaggeredActivity : AppCompatActivity() {
  private lateinit var binding: StaggeredActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.staggered_act)
    
  }
}
