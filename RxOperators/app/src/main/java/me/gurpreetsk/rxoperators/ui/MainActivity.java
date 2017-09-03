package me.gurpreetsk.rxoperators.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.gurpreetsk.rxoperators.R;
import me.gurpreetsk.rxoperators.ui.combination.CombinationOperatorsActivity;
import me.gurpreetsk.rxoperators.ui.filtering.FilteringOperatorsActivity;
import me.gurpreetsk.rxoperators.ui.mathematical.MathematicalOperatorsActivity;
import me.gurpreetsk.rxoperators.ui.transformation.TransformationOperatorsActivity;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.button_transformation)
  public void openTransformationActivity() {
    startActivity(new Intent(MainActivity.this, TransformationOperatorsActivity.class));
  }

  @OnClick(R.id.button_combinational)
  public void openCombinationalActivity() {
    startActivity(new Intent(MainActivity.this, CombinationOperatorsActivity.class));
  }

  @OnClick(R.id.button_filtering)
  public void openFilteringActivity() {
    startActivity(new Intent(MainActivity.this, FilteringOperatorsActivity.class));
  }

  @OnClick(R.id.button_mathematical)
  public void openMathematicalActivity() {
    startActivity(new Intent(MainActivity.this, MathematicalOperatorsActivity.class));
  }

}
