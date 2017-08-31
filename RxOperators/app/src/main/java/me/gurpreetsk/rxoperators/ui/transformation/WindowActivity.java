package me.gurpreetsk.rxoperators.ui.transformation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.rxoperators.R;

public class WindowActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_window);

    Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .subscribeOn(Schedulers.io())
//        .window(2)
        .groupBy(new Function<Integer, Integer>() {
          @Override
          public Integer apply(@NonNull Integer integer) throws Exception {
            if (integer % 2 == 0)
              return integer;
            return null;
          }
        })
    ;

  }
}
