package me.gurpreetsk.rxoperators.ui.transformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class FlatmapActivity extends AppCompatActivity {

  @BindView(R.id.textview_flatmap)
  TextView textviewFlatmap;

  Disposable disposable;

  private static final String TAG = MapActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flatmap);
    ButterKnife.bind(this);
  }

  @Override
  protected void onStart() {
    super.onStart();

  }

  @Override
  protected void onStop() {
    super.onStop();
    // dispose off the used resources
    if (!disposable.isDisposed())
      disposable.dispose();
  }

}
