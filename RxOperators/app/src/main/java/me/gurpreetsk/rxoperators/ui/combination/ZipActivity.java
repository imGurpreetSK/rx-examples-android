package me.gurpreetsk.rxoperators.ui.combination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import me.gurpreetsk.rxoperators.R;

public class ZipActivity extends AppCompatActivity {

  @BindView(R.id.textview_zip)
  TextView textviewZip;

  private static final String TAG = ZipActivity.class.getSimpleName();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zip);
    ButterKnife.bind(this);
    setTitle(TAG);

    doStuff();

  }

  private void doStuff() {
    Observable.zip(getBoyNames(), getRollNumber(), new BiFunction<String, Integer, String>() {
      @Override
      public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
        return s + integer;
      }
    }).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<String>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {

          }

          @Override
          public void onNext(@NonNull String s) {

          }

          @Override
          public void onError(@NonNull Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });
  }

  Observable<String> getBoyNames() {
    return Observable.fromArray("Manish", "David", "Rohan", "Gurpreet");
  }

  Observable<Integer> getRollNumber() {
    return Observable.fromArray(12, 15, 63, 13, 54, 92);
  }

}
