package me.gurpreetsk.rxoperators.ui.transformation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.rxoperators.R;
import me.gurpreetsk.rxoperators.model.GithubResults;
import me.gurpreetsk.rxoperators.model.Project;
import me.gurpreetsk.rxoperators.model.GithubUser;
import me.gurpreetsk.rxoperators.rest.ApiClient;
import me.gurpreetsk.rxoperators.rest.ApiInterface;
import me.gurpreetsk.rxoperators.util.SeeLink;
import retrofit2.Response;

public class FlatmapActivity extends AppCompatActivity {

  @BindView(R.id.textview_flatmap)
  TextView textviewFlatmap;

  ApiInterface apiService;

  private static final String TAG = FlatmapActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flatmap);
    ButterKnife.bind(this);
    setTitle(TAG);

    apiService =
        ApiClient.getClient().create(ApiInterface.class);

  }

  @Override
  protected void onStart() {
    super.onStart();
    exampleWork();
  }

  @SeeLink(links = {"https://stackoverflow.com/questions/22847105/when-do-you-use-map-vs-flatmap-in-rxjava"})
  private void exampleWork() {
    apiService.getGithubRepos("rxjava")
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        // the FlatMap operator does some processing on each element passed by the observable to it
        // and returns an *observable* for each element
        .flatMap(new Function<Response<GithubResults>, ObservableSource<Project>>() {
          @Override
          public ObservableSource<Project> apply(@NonNull Response<GithubResults> githubResultsResponse) throws Exception {
            return Observable.fromIterable(githubResultsResponse.body().getItems());
          }
        })
        .flatMap(new Function<Project, ObservableSource<GithubUser>>() {
          @Override
          public Observable<GithubUser> apply(@NonNull Project project) throws Exception {
            return apiService.getUserInfo(project.getOwner().getLogin());
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .take(5)
        .subscribe(new Observer<GithubUser>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {

          }

          @Override
          public void onNext(@NonNull GithubUser user) {
            Log.d(TAG, "apply: " + user.getLogin());
            textviewFlatmap.setText(textviewFlatmap.getText() + "\n" + user.getLogin());
          }

          @Override
          public void onError(@NonNull Throwable e) {
            Toast.makeText(FlatmapActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onError: ", e);
          }

          @Override
          public void onComplete() {

          }
        });
  }

  @Override
  protected void onStop() {
    super.onStop();
    // dispose off the used resources
//    if (!disposable.isDisposed())
//      disposable.dispose();
  }


}
