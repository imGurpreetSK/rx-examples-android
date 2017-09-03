package me.gurpreetsk.rxoperators.ui.combination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.gurpreetsk.rxoperators.R;

public class CombinationOperatorsActivity extends AppCompatActivity {

  private static final String TAG = CombinationOperatorsActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_combinational_operators);
    setTitle(TAG);
  }
}
