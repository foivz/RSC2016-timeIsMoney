package com.rsc.rschackathon.api;

import com.rsc.rschackathon.api.models.DocsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @GET("/json/movies.json")
    Call<List<DocsApi>> getRepositories(/*@Query("q") String name, @Query("sort") String sort*/);

    @FormUrlEncoded
    @POST("/api/v1/auth/facebook")
    public Call<DocsApi> getApiKey(/*@Field("accessToken") String accessToken*/);
}
