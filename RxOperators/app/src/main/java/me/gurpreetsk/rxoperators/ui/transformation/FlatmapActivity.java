package me.gurpreetsk.rxoperators.ui.transformation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.rxoperators.R;
import me.gurpreetsk.rxoperators.rest.ApiClient;
import me.gurpreetsk.rxoperators.rest.ApiInterface;
import me.gurpreetsk.rxoperators.util.SeeLink;

public class FlatmapActivity extends AppCompatActivity {

  @BindView(R.id.textview_flatmap)
  TextView textviewFlatmap;

  Disposable disposable;
  ApiInterface apiService;

  private static final String TAG = MapActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flatmap);
    ButterKnife.bind(this);
    setTitle(TAG);

    apiService =
        ApiClient.getClient().create(ApiInterface.class);

  }

  @Override
  protected void onStart() {
    super.onStart();
    exampleWork();
  }

  @SeeLink(links = {"https://stackoverflow.com/questions/22847105/when-do-you-use-map-vs-flatmap-in-rxjava"})
  private void exampleWork() {
    disposable = Observable.just(2, 4, 6, 8, 10)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        // the FlatMap operator does some processing on each element passed by the observable to it
        // and returns an *observable* for each element
        .flatMap(new Function<Integer, ObservableSource<Integer>>() {
          @Override
          public ObservableSource<Integer> apply(@NonNull Integer integer) throws Exception {
            return Observable.just((int) Math.pow(integer, 2), (int) Math.pow(integer, 3));
          }
        })
//        .take(2)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Integer>() {
          @Override
          public void accept(Integer integer) throws Exception {
            textviewFlatmap.setText(textviewFlatmap.getText() + "\n" + integer);
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
