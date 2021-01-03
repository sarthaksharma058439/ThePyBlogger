package com.sarthak01.sarthak.thepyblogger;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL="https://www.thepyblogger.com/api/";

    @GET("postseries")
    Call<List<PostSeries>> getPostSeries();

    @GET("post")
    Call<List<Post>> getPost();
}
