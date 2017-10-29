package me.gurpreetsk.rxoperators.ui.combination;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import me.gurpreetsk.rxoperators.R;

public class CombineLatestActivity extends AppCompatActivity {

  @BindView(R.id.textview_combine_latest)
  TextView textviewCombine;

  private static final String TAG = CombineLatestActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_combine_latest);
    ButterKnife.bind(this);
    setTitle(TAG);

    doStuff();
  }

  private void doStuff() {
    StringBuilder builder = new StringBuilder();
    //combine two observables
    //notice the sizes of Observables and the output
    Observable.combineLatest(
        getBoyNames(), getRollNumber(),
        (s, integer) -> s + " -> " + integer)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<String>() {
          Disposable d;

          @Override
          public void onSubscribe(@NonNull Disposable d) {
            this.d = d;
          }

          @Override
          public void onNext(@NonNull String s) {
            builder.append(s).append("\n");
          }

          @Override
          public void onError(@NonNull Throwable e) {
            Log.e(TAG, "onError: ", e);
            if (!d.isDisposed())
              d.isDisposed();
          }

          @Override
          public void onComplete() {
            textviewCombine.setText(builder.toString());
            if (!d.isDisposed())
              d.isDisposed();
          }
        });
  }

  Observable<String> getBoyNames() {
    return Observable.fromArray("Manish", "David", "Rohan", "Gurpreet");
  }

  Observable<Integer> getRollNumber() {
    return Observable.fromArray(12, 15, 63, 15, 54, 92);
  }

}
