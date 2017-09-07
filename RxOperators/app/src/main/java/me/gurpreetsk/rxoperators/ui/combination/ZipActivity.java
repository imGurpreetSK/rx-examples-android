package me.gurpreetsk.rxoperators.ui.combination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gurpreetsk.rxoperators.R;

public class ZipActivity extends AppCompatActivity {

  @BindView(R.id.textview_zip)
  TextView textviewZip;

  private static final String TAG = ZipActivity.class.getSimpleName();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zip);
    ButterKnife.bind(this);
    setTitle(TAG);
  }



}
