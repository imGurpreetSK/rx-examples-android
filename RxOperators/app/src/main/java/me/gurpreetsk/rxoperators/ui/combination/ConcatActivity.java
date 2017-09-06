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
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.rxoperators.R;

public class ConcatActivity extends AppCompatActivity {

  @BindView(R.id.textview_concat)
  TextView textviewConcat;

  StringBuilder builder = new StringBuilder();

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
    Observable.concat(getBoyNames(), getGirlNames())
        .subscribeOn(Schedulers.io())
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
              d.dispose();
          }

          @Override
          public void onComplete() {
            textviewConcat.setText(builder.toString());
            if (!d.isDisposed())
              d.dispose();
          }
        });
  }

  @Override
  protected void onStop() {
    super.onStop();
    builder = null;
  }

  Observable<String> getBoyNames() {
    return Observable.fromArray("Manish", "David", "Rohan", "Gurpreet");
  }

  Observable<String> getGirlNames() {
    return Observable.fromArray("Monishaa", "Sonakshi", "Aravi", "Stephanie");
  }

}
