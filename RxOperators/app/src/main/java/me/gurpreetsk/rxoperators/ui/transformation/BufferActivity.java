package me.gurpreetsk.rxoperators.ui.transformation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.rxoperators.R;

public class BufferActivity extends AppCompatActivity {

  @BindView(R.id.textview_buffer)
  TextView textviewBuffer;

  List<String> list = new ArrayList<>();
  int total = 0;

  private static final String TAG = BufferActivity.class.getSimpleName();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_buffer);
    ButterKnife.bind(this);
    setTitle(TAG);
    setup();

    Observable.fromIterable(list)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        //buffers the results and emits after a given timespan
        //Do read docs(cmd+click on mac)
        .buffer(2, TimeUnit.MICROSECONDS, 5)
        .map(new Function<List<String>, Integer>() {
          @Override
          public Integer apply(@NonNull List<String> strings) throws Exception {
            //size of current emission
            int size = strings.size();
            Log.i(TAG, "Last emission size: " + size);
            total += size;
            return size;
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Integer>() {
          @Override
          public void accept(Integer count) throws Exception {
            textviewBuffer.setText("TOTAL EMITTED: " + total);
          }
        });
  }

  private void setup() {
    for (int i = 0; i < 50; i++) {
      list.add("hello");
      list.add("world");
      list.add("new");
      list.add("task");
      list.add("buffer");
      list.add("operator");
      list.add("howdy!");
      list.add("boy");
    }
  }


}
