package me.gurpreetsk.rxretrofit.rest;

import io.reactivex.Observable;
import me.gurpreetsk.rxretrofit.model.GithubResults;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gurpreet on 18/08/17.
 */

public interface ApiInterface {

    @GET("/search/repositories")
    Observable<Response<GithubResults>> getGithubRepos(@Query("q") String query);

}
