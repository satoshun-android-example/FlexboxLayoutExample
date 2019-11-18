package com.github.satoshun.example.diff

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.databinding.DiffActBinding

class DiffActivity : AppCompatActivity() {
  private lateinit var binding: DiffActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DiffActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.recycler.layoutManager = LinearLayoutManager(this)
    binding.recycler.adapter = BasicRecyclerViewAdapter()
  }
}

data class Data(
  @LayoutRes val layoutId: Int,
  val text: String
)

class BasicRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getItemCount(): Int {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
