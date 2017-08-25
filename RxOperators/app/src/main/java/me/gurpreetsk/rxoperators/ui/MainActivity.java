package me.gurpreetsk.rxoperators.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.gurpreetsk.rxoperators.R;
import me.gurpreetsk.rxoperators.ui.transformation.FlatmapActivity;
import me.gurpreetsk.rxoperators.ui.transformation.MapActivity;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.button_map_operator)
  public void openMapActivity() {
    startActivity(new Intent(MainActivity.this, MapActivity.class));
  }

  @OnClick(R.id.button_flatmap_operator)
  public void openFlatmapActivity() {
    startActivity(new Intent(MainActivity.this, FlatmapActivity.class));
  }

}
