package com.rsc.rschackathon.api;

import com.rsc.rschackathon.api.models.DocsApi;
import com.rsc.rschackathon.api.models.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @GET("/json/movies.json")
    Call<List<DocsApi>> getRepositories(/*@Query("q") String name, @Query("sort") String sort*/);

    @FormUrlEncoded
    @POST("/api/login")
    public Call<LoginResponse> getApiKey(@Field("provider") String provider, @Field("access_token") String accessToken);
}
