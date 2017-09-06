package me.gurpreetsk.rxoperators.ui.combination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import me.gurpreetsk.rxoperators.R;

public class ConcatActivity extends AppCompatActivity {

  @BindView(R.id.textview_concat)
  TextView textviewConcat;

  private static final String TAG = ConcatActivity.class.getSimpleName();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_concat);
    ButterKnife.bind(this);
    setTitle(TAG);
  }

  @Override
  protected void onStart() {
    super.onStart();

  }

  Observable<String> getBoyNames() {
    return Observable.fromArray("Manish", "David", "Rohan", "Gurpreet");
  }

  Observable<String> getGirlNames() {
    return Observable.fromArray("Monishaa", "Sonakshi", "Aravi", "Stephanie");
  }

}
