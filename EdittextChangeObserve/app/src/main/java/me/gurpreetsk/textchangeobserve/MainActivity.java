package me.gurpreetsk.textchangeobserve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.textchangeobserve.model.GithubResults;
import me.gurpreetsk.textchangeobserve.rest.ApiClient;
import me.gurpreetsk.textchangeobserve.rest.ApiInterface;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  RecyclerView recyclerView;
  ProgressBar progressBar;
  EditText editText;

  ApiInterface apiService;

  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    editText = (EditText) findViewById((R.id.edittext_query));

    progressBar.setIndeterminate(true);
    progressBar.setVisibility(View.VISIBLE);

    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,
        DividerItemDecoration.VERTICAL));

    apiService = ApiClient.getClient().create(ApiInterface.class);
  }

  @Override
  protected void onStart() {
    super.onStart();
    getResults();
  }

  /**
   * @return Observable of query items entered in the edittext
   */
  private Observable<String> getTextObservable() {

    final Observable<String> textObservable = Observable.create(new ObservableOnSubscribe<String>() {
      @Override
      public void subscribe(@NonNull final ObservableEmitter<String> emitter) throws Exception {
        final TextWatcher watcher = new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {
          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            emitter.onNext(s.toString());
          }

          @Override
          public void afterTextChanged(Editable s) {
          }
        };
        emitter.setCancellable(new Cancellable() {  //dispose off any resources/listeners
          @Override
          public void cancel() throws Exception {
            editText.removeTextChangedListener(watcher);
          }
        });
      }
    });

    return textObservable.filter(new Predicate<String>() {
      @Override
      public boolean test(@NonNull String s) throws Exception {
        //onNext to be called only of this returns true
        return s.length() > 2;
      }
    }).debounce(500, TimeUnit.MILLISECONDS);  //lossy searching: See marble diagram

  }

  /**
   * get results from the API
   */
  private void getResults() {

    getTextObservable().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(new Consumer<String>() {  //do this every time a new item is emitted
          @Override
          public void accept(String s) throws Exception {
            progressBar.setVisibility(View.VISIBLE);
          }
        })
        .subscribe(new Consumer<String>() {
          @Override
          public void accept(String query) throws Exception {
            //get the results from API
            apiService.getGithubRepos(query)
                .subscribeOn(Schedulers.io())   //create on io thread
                .observeOn(AndroidSchedulers.mainThread())  //observe the response on Main Thread
                .retry(5)
                .subscribe(new Observer<Response<GithubResults>>() {
                  Disposable d;

                  @Override
                  public void onSubscribe(@NonNull Disposable d) {
                    this.d = d;
                  }

                  @Override
                  public void onNext(@NonNull Response<GithubResults> githubResultsResponse) {
                    //The results are presented here
                    progressBar.setVisibility(View.GONE);
                    Log.d(TAG, "onNext: code: " + githubResultsResponse.code());

                    if (githubResultsResponse.code() == 200) {
                      Log.i(TAG, "onNext: " + githubResultsResponse.body().getItems().size());
                      recyclerView.setAdapter(
                          new GithubAdapter(MainActivity.this, githubResultsResponse.body().getItems()));
                    } else {
                      Log.e(TAG, "onNext: error: " + githubResultsResponse.errorBody().toString());
                    }
                  }

                  @Override
                  public void onError(@NonNull Throwable e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this,
                        e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    if (!d.isDisposed())    //dispose the resource if not done already
                      d.dispose();
                  }

                  @Override
                  public void onComplete() {
                    progressBar.setVisibility(View.GONE);
                    if (!d.isDisposed())    //dispose the resource if not done already
                      d.dispose();
                  }
                });
          }
        });

  }

}
