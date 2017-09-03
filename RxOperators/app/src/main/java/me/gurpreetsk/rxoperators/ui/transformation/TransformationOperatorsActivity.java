package me.gurpreetsk.rxoperators.ui.transformation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.gurpreetsk.rxoperators.R;
import me.gurpreetsk.rxoperators.util.SeeLink;

public class TransformationOperatorsActivity extends AppCompatActivity {

  private static final String TAG = TransformationOperatorsActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transformation_operators);
    ButterKnife.bind(this);
    setTitle(TAG);
  }

  @OnClick(R.id.button_map_operator)
  public void openMapActivity() {
    startActivity(new Intent(TransformationOperatorsActivity.this, MapActivity.class));
  }

  @SeeLink(links = {"https://github.com/ReactiveX/RxJava/wiki/Transforming-Observables"})
  @OnClick(R.id.button_flatmap_operator)
  public void openFlatmapActivity() {
    startActivity(new Intent(TransformationOperatorsActivity.this, FlatmapActivity.class));
  }

  @OnClick(R.id.button_buffer_operator)
  public void openBufferActivity() {
    startActivity(new Intent(TransformationOperatorsActivity.this, BufferActivity.class));
  }

  @OnClick(R.id.button_window_operator)
  public void openWindowActivity() {
    startActivity(new Intent(TransformationOperatorsActivity.this, WindowActivity.class));
  }

}
