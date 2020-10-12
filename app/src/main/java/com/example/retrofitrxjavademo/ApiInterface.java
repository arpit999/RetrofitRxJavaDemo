package com.example.retrofitrxjavademo;

import com.example.retrofitrxjavademo.model.Language;
import com.example.retrofitrxjavademo.model.MovieResopnse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MovieResopnse> getTopRatedMovie(@Query("api_key") String apiKey);

    @GET("configuration/languages")
    Call<List<Language>> getLanguages(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResopnse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
