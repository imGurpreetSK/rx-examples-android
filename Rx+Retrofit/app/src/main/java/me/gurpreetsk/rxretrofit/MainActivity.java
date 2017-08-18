package me.gurpreetsk.rxretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.gurpreetsk.rxretrofit.model.GithubResults;
import me.gurpreetsk.rxretrofit.rest.ApiClient;
import me.gurpreetsk.rxretrofit.rest.ApiInterface;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        apiService.getGithubRepos("rx")
                .subscribeOn(Schedulers.io())   //create on io thread
                .observeOn(AndroidSchedulers.mainThread())  //observe the response on Main Thread
                .subscribe(new Observer<Response<GithubResults>>() {
                    Disposable d;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        this.d = d;
                    }

                    @Override
                    public void onNext(@NonNull Response<GithubResults> githubResultsResponse) {
                        Log.d(TAG, "onNext: code: " + githubResultsResponse.code());
                        if (githubResultsResponse.code() == 200) {
                            Log.i(TAG, "onNext: "+githubResultsResponse.body().getItems());
                        } else {
                            Log.e(TAG, "onNext: error: " + githubResultsResponse.errorBody().toString());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(MainActivity.this,
                                e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onError: ", e);
                        if (!d.isDisposed())
                            d.dispose();
                    }

                    @Override
                    public void onComplete() {
                        if (!d.isDisposed())
                            d.dispose();
                    }
                });

    }
}
