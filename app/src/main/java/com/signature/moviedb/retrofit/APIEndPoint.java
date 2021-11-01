package com.signature.moviedb.retrofit;

import com.signature.moviedb.model.Movies;
import com.signature.moviedb.model.NowPlaying;
import com.signature.moviedb.model.Popular;
import com.signature.moviedb.model.Reviews;
import com.signature.moviedb.model.Upcoming;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIEndPoint {

    @GET("movie/{movie_id}")
    Call<Movies> getMovieByID(
            @Path("movie_id") String movieID,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}")
    Call<Movies> getMovieelements(
            @Query("api_key") String apiKey
    );

    @GET("movie/now_playing")
    Call<NowPlaying> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/reviews")
    Call<Reviews> getReview(
            @Query("api_key") String apiKey
    );

    @GET("movie/upcoming")
    Call<Upcoming> getUpcoming(
            @Query("api_key") String apiKey
    );

    @GET("movie/popular")
    Call<Popular> getPopular(
            @Query("api_key") String apiKey
    );
}
