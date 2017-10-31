package me.gurpreetsk.rxoperators.ui.filtering

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_distinct_operator.*
import me.gurpreetsk.rxoperators.R

class DistinctOperatorActivity : AppCompatActivity() {
  private val TAG = DistinctOperatorActivity::class.simpleName

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_distinct_operator)
    title = TAG
  }

  override fun onStart() {
    super.onStart()
    Observable.just("gurpreet", "jatin", "jatin", "ashish", "gurpreet", "afnan")
        .subscribeOn(Schedulers.io())
        .distinct()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          textviewDistinctOutput.text = "${textviewDistinctOutput.text} \n $it"
        })
  }
}
