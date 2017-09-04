package me.gurpreetsk.rxoperators.ui.combination;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.rxoperators.R;

public class MergeActivity extends AppCompatActivity {

  @BindView(R.id.textview_merge)
  TextView textviewMerge;

  private static final String TAG = MergeActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_merge);
    ButterKnife.bind(this);
    setTitle(TAG);
  }

  @Override
  protected void onStart() {
    super.onStart();
    final StringBuilder names = new StringBuilder();
    //Merge the two observables
    //(See marble diagram in README for difference between Merge and Concat)
    Observable.merge(getGirlNames(), getBoyNames())
        .observeOn(Schedulers.computation())
        .subscribe(new Consumer<String>() {
          @Override
          public void accept(String s) throws Exception {
            names.append(s);
          }
        });
    textviewMerge.setText(names.toString());
  }

  Observable<String> getBoyNames() {
    return Observable.fromArray("Manish", "David", "Rohan", "Gurpreet");
  }

  Observable<String> getGirlNames() {
    return Observable.fromArray("Monishaa", "Sonakshi", "Aravi", "Stephanie");
  }

}
