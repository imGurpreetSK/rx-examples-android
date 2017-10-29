package me.gurpreetsk.rxoperators.ui.filtering

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.gurpreetsk.rxoperators.R

class DistinctOperatorActivity : AppCompatActivity() {
    private val TAG = DistinctOperatorActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distinct_operator)
        title = TAG
    }
}
