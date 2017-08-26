package me.gurpreetsk.rxoperators.ui.transformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.rxoperators.R;
import me.gurpreetsk.rxoperators.rest.ApiClient;
import me.gurpreetsk.rxoperators.rest.ApiInterface;

public class MapActivity extends AppCompatActivity {

  @BindView(R.id.textview_map)
  TextView textviewMap;

  Disposable disposable;

  private static final String TAG = MapActivity.class.getSimpleName();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);
    ButterKnife.bind(this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    disposable = Observable.just(2, 4, 6, 8, 10)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        // the map operator does some processing on each element passed by the observable to it
        // here, it takes each integer, processes it and returns a new integer
        // an Observable of integers is finally returned in the chain
        .map(new Function<Integer, Integer>() {
          @Override
          public Integer apply(@NonNull Integer integer) throws Exception {
            return integer * integer;
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Integer>() {
          @Override
          public void accept(Integer integer) throws Exception {
            textviewMap.setText(textviewMap.getText() + " " + integer);
          }
        });
  }

  @Override
  protected void onStop() {
    super.onStop();
    // dispose off the used resources
    if (!disposable.isDisposed())
      disposable.dispose();
  }

}
