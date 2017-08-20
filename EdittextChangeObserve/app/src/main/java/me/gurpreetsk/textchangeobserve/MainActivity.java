package me.gurpreetsk.textchangeobserve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.textchangeobserve.model.GithubResults;
import me.gurpreetsk.textchangeobserve.rest.ApiClient;
import me.gurpreetsk.textchangeobserve.rest.ApiInterface;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,
                DividerItemDecoration.VERTICAL));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        apiService.getGithubRepos("rxjava")
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
}
