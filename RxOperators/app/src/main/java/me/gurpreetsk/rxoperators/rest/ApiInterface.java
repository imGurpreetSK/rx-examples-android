package me.gurpreetsk.rxoperators.rest;

import io.reactivex.Observable;
import me.gurpreetsk.rxoperators.model.github.GithubResults;
import me.gurpreetsk.rxoperators.model.github.GithubUser;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gurpreet on 25/08/17.
 */

public interface ApiInterface {

  @GET("/search/repositories")
  Observable<Response<GithubResults>> getGithubRepos(@Query("q") String query);

  @GET("/users/{username}")
  Observable<GithubUser> getUserInfo(@Path("username") String username);

  @GET("/search/repositories")
  Call<GithubResults> callGithubRepos(@Query("q") String query);

  @GET("/users/{username}")
  Call<GithubUser> callUserInfo(@Path("username") String username);

}
