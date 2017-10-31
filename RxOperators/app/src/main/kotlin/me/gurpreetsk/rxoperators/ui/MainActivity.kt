package me.gurpreetsk.rxoperators.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_main.*
import me.gurpreetsk.rxoperators.R
import me.gurpreetsk.rxoperators.ui.combination.CombinationOperatorsActivity
import me.gurpreetsk.rxoperators.ui.filtering.FilteringOperatorsActivity
import me.gurpreetsk.rxoperators.ui.mathematical.MathematicalOperatorsActivity
import me.gurpreetsk.rxoperators.ui.transformation.TransformationOperatorsActivity

class MainActivity : AppCompatActivity() {
  private val TAG = MainActivity::class.java.simpleName

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    ButterKnife.bind(this)

    button_transformation.setOnClickListener({
      startActivity(Intent(this@MainActivity, TransformationOperatorsActivity::class.java))
    })
    button_combinational.setOnClickListener({
      startActivity(Intent(this@MainActivity, CombinationOperatorsActivity::class.java))
    })
    button_filtering.setOnClickListener({
      startActivity(Intent(this@MainActivity, FilteringOperatorsActivity::class.java))
    })
    button_mathematical.setOnClickListener({
      startActivity(Intent(this@MainActivity, MathematicalOperatorsActivity::class.java))
    })
  }
}
