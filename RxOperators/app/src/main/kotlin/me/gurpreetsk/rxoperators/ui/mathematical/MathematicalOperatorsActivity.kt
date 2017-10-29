package me.gurpreetsk.rxoperators.ui.mathematical

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.gurpreetsk.rxoperators.R

class MathematicalOperatorsActivity : AppCompatActivity() {
    companion object {
        private val TAG = MathematicalOperatorsActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mathematical_operators)
        title = TAG
    }
}
