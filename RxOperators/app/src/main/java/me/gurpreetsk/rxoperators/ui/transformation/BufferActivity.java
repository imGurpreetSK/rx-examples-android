package me.gurpreetsk.rxoperators.ui.transformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gurpreetsk.rxoperators.R;

public class BufferActivity extends AppCompatActivity {

  @BindView(R.id.textview_buffer)
  TextView textviewBuffer;

  private static final String TAG = BufferActivity.class.getSimpleName();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_buffer);
    ButterKnife.bind(this);
  }



}
