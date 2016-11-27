package com.rsc.rschackathon.api;

import com.rsc.rschackathon.api.models.AddTeamMemberApi;
import com.rsc.rschackathon.api.models.AnswerQuestionModel;
import com.rsc.rschackathon.api.models.CreateTeamApi;
import com.rsc.rschackathon.api.models.CurrentEvent;
import com.rsc.rschackathon.api.models.DocsApi;
import com.rsc.rschackathon.api.models.GetTeamMembersAPI;
import com.rsc.rschackathon.api.models.LoginResponse;
import com.rsc.rschackathon.api.models.Question;
import com.rsc.rschackathon.api.models.QustionAPIModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {

    @GET("/json/movies.json")
    Call<List<DocsApi>> getRepositories(/*@Query("q") String name, @Query("sort") String sort*/);

    @FormUrlEncoded
    @POST("/api/login")
    public Call<LoginResponse> getApiKey(@Field("provider") String provider, @Field("access_token") String accessToken);

    @GET("/api/events/current")
    Call<CurrentEvent> getCurrentEvents();

    @GET("/api/events")
    Call<CurrentEvent> getAllEvents();

    @FormUrlEncoded
    @POST("/api/team")
    public Call<CreateTeamApi> createTeam(@Header("X-Authorization") String apyKey, @Field("event_id") int eventId,
            @Field("team_name") String teamName);

    @GET("/api/team/{team_id}/members")
    public Call<GetTeamMembersAPI> getTeamMembers(@Path("team_id") int id, @Header("X-Authorization") String apyKey);

    @GET("/api/event/{quizz_id}/question")
    public Call<QustionAPIModel> getQuestions(@Path("quizz_id") int id, @Header("X-Authorization") String apyKey);

    @FormUrlEncoded
    @POST("/api/team/join")
    public Call<AddTeamMemberApi> addTeamMembers(@Header("X-Authorization") String apyKey, @Field("team_id") int teamId,
            @Field("user_id") int userId);

    @GET("/api/question/5")
    Call<Question> getQuestion();

    @FormUrlEncoded
    @POST("/api/answer")
    public Call<AnswerQuestionModel> answerQuestion(@Header("X-Authorization") String apyKey, @Field("team_id") int teamId,
            @Field("answer_id") int userId);

    @FormUrlEncoded
    @POST("/api/tiebreaker")
    public Call<AnswerQuestionModel> tieBreaker(@Header("X-Authorization") String apyKey, @Field("team_id") int teamId,
            @Field("score") int score);
}
