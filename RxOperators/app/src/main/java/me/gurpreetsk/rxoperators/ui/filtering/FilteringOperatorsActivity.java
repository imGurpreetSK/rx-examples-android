package me.gurpreetsk.rxoperators.ui.filtering;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.gurpreetsk.rxoperators.R;

public class FilteringOperatorsActivity extends AppCompatActivity {

  private static final String TAG = FilteringOperatorsActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_filtering_operators);
    setTitle(TAG);
  }
}
