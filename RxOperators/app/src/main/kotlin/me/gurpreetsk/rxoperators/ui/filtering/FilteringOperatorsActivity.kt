package me.gurpreetsk.rxoperators.ui.filtering

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.gurpreetsk.rxoperators.R

class FilteringOperatorsActivity : AppCompatActivity() {
    private val TAG = FilteringOperatorsActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtering_operators)
        title = TAG
    }
}
