package me.gurpreetsk.asynctasksubstitute;

import android.os.SystemClock;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final TextView textview = (TextView) findViewById(R.id.textview);

    getObservable()
        // Do all the work on the computation thread.
        // Other options are io, newThread
        .observeOn(Schedulers.computation())
        // Map function does some operation on each element emitted and returns an observable
        .map(new Function<List<Integer>, Integer>() {
          // Gets data from 'e.onNext(Arrays.asList(1, 2, 3, 4, 5))'
          @Override
          public Integer apply(@NonNull List<Integer> integers) throws Exception {
            int prod = 1;
            for (int i : integers) {
              prod *= i;
              SystemClock.sleep(700);
            }
            return prod;
          }
        })
        // All operations below this will happen on Android's MainThread
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Integer>() {
          Disposable d;

          @Override
          public void onSubscribe(@NonNull Disposable d) {
            this.d = d;
          }

          @Override
          public void onNext(@NonNull Integer prod) {
            //Update UI
            textview.setText("The product is " + prod);
          }

          @Override
          public void onError(@NonNull Throwable e) {
            e.printStackTrace();
          }

          @Override
          public void onComplete() {
            if (!d.isDisposed())
              d.dispose();
          }
        });

  }

  private Observable<List<Integer>> getObservable() {
    return Observable.create(new ObservableOnSubscribe<List<Integer>>() {
      @Override
      public void subscribe(@NonNull ObservableEmitter<List<Integer>> e) throws Exception {
        e.onNext(Arrays.asList(1, 2, 3, 4, 5));
      }
    });
  }

}
