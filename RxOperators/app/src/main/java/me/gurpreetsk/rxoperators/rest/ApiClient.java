package me.gurpreetsk.rxoperators.rest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gurpreet on 25/08/17.
 */

public class ApiClient {

  private static final String BASE_URL = "https://api.github.com";
  private static Retrofit retrofit = null;


  public static Retrofit getClient() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return retrofit;
  }

}
