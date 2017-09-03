package me.gurpreetsk.rxoperators.ui.transformation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.rxoperators.R;

public class WindowActivity extends AppCompatActivity {

  @BindView(R.id.textview_window)
  TextView textView;

  int count;

  private static final String TAG = WindowActivity.class.getSimpleName();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_window);
    ButterKnife.bind(this);

    Observable.interval(1, TimeUnit.SECONDS).take(10)
        .subscribeOn(Schedulers.io())
        .window(3)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Observable<Long>>() {
          Disposable d;

          @Override
          public void onSubscribe(@NonNull Disposable d) {
            this.d = d;
          }

          @Override
          public void onNext(@NonNull Observable<Long> longObservable) {
            Log.i(TAG, "onNext: NEW WINDOW");
            count++;
            longObservable.observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                  @Override
                  public void accept(Long aLong) throws Exception {
                    Log.i(TAG, "accept: " + aLong);
                  }
                });
            textView.setText("Window count: " + count);
          }

          @Override
          public void onError(@NonNull Throwable e) {
            Toast.makeText(WindowActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            if (!d.isDisposed())
              d.dispose();
          }

          @Override
          public void onComplete() {
            if (!d.isDisposed())
              d.dispose();
          }
        });

  }
}
