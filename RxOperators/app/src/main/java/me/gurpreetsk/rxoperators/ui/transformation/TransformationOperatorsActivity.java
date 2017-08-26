package me.gurpreetsk.rxoperators.ui.transformation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.gurpreetsk.rxoperators.R;

public class TransformationOperatorsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transformation_operators);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.button_map_operator)
  public void openMapActivity() {
    startActivity(new Intent(TransformationOperatorsActivity.this, MapActivity.class));
  }

  @OnClick(R.id.button_flatmap_operator)
  public void openFlatmapActivity() {
    startActivity(new Intent(TransformationOperatorsActivity.this, FlatmapActivity.class));
  }

}
