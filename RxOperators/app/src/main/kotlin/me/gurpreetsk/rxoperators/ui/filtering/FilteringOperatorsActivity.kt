package me.gurpreetsk.rxoperators.ui.filtering

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_filtering_operators.*
import me.gurpreetsk.rxoperators.R

class FilteringOperatorsActivity : AppCompatActivity() {
    private val TAG = FilteringOperatorsActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtering_operators)
        title = TAG
        setupButtons()
    }

    private fun setupButtons() {
        buttonDistinct.setOnClickListener({
            startActivity(Intent(FilteringOperatorsActivity@this, DistinctOperatorActivity::class.java))
        })
//        buttonDistinctUntilChanged.setOnClickListener({
//            startActivity(Intent(FilteringOperatorsActivity@this, DistinctUntilChangedOperatorActivity::class.java))
//        })
//        buttonDebounce.setOnClickListener({
//            startActivity(Intent(FilteringOperatorsActivity@this, DistinctOperatorActivity::class.java))
//        })
//        buttonThrottle.setOnClickListener({
//            startActivity(Intent(FilteringOperatorsActivity@this, DistinctOperatorActivity::class.java))
//        })
//        buttonTakeUntil.setOnClickListener({
//            startActivity(Intent(FilteringOperatorsActivity@this, DistinctOperatorActivity::class.java))
//        })
//        buttonFilter.setOnClickListener({
//            startActivity(Intent(FilteringOperatorsActivity@this, DistinctOperatorActivity::class.java))
//        })
//        buttonFirst.setOnClickListener({
//            startActivity(Intent(FilteringOperatorsActivity@this, DistinctOperatorActivity::class.java))
//        })
    }
}
