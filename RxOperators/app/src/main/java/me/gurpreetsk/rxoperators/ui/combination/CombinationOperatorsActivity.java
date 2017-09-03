package me.gurpreetsk.rxoperators.ui.combination;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.gurpreetsk.rxoperators.R;

public class CombinationOperatorsActivity extends AppCompatActivity {

  private static final String TAG = CombinationOperatorsActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_combinational_operators);
    ButterKnife.bind(this);
    setTitle(TAG);
  }

  @OnClick(R.id.button_merge)
  public void openMergeActivity() {

  }

  @OnClick(R.id.button_concat)
  public void openConcatActivity() {

  }

  @OnClick(R.id.button_zip)
  public void openZipActivity() {

  }

  @OnClick(R.id.button_combine_latest)
  public void openCombineLatestActivity() {

  }

  @OnClick(R.id.button_race)
  public void openRaceActivity() {

  }

}
