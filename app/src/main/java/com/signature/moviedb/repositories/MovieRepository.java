package com.signature.moviedb.repositories;

import androidx.lifecycle.MutableLiveData;

import com.signature.moviedb.helper.Const;
import com.signature.moviedb.model.Movies;
import com.signature.moviedb.model.NowPlaying;
import com.signature.moviedb.model.Popular;
import com.signature.moviedb.model.Reviews;
import com.signature.moviedb.model.Upcoming;
import com.signature.moviedb.retrofit.APIservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository repository;

    private MovieRepository(){}

    public static MovieRepository getInstance(){
        if (repository == null){
            repository = new MovieRepository();
        }
        return repository;
    }

    public MutableLiveData<Movies> getMovieData(String movieId){
        final MutableLiveData<Movies> result = new MutableLiveData<>();

        APIservice.endPoint().getMovieByID(movieId, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

        return result;
    }


    public MutableLiveData<Movies> getMovieDataelements(){
        final MutableLiveData<Movies> result = new MutableLiveData<>();

        APIservice.endPoint().getMovieelements(Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<NowPlaying> getNowPlayingData(){
        final MutableLiveData<NowPlaying> result = new MutableLiveData<>();
        APIservice.endPoint().getNowPlaying(Const.API_KEY).enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<Reviews> getReviewData(){
        final MutableLiveData<Reviews> result = new MutableLiveData<>();
        APIservice.endPoint().getReview(Const.API_KEY).enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {

            }


        });

        return result;
    }


    public MutableLiveData<Upcoming> getUpcomingData(){
        final MutableLiveData<Upcoming> result = new MutableLiveData<>();
        APIservice.endPoint().getUpcoming(Const.API_KEY).enqueue(new Callback<Upcoming>() {
            @Override
            public void onResponse(Call<Upcoming> call, Response<Upcoming> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Upcoming> call, Throwable t) {

            }

        });

        return result;
    }


    public MutableLiveData<Popular> getPopularData(){
        final MutableLiveData<Popular> result = new MutableLiveData<>();
        APIservice.endPoint().getPopular(Const.API_KEY).enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {

            }

        });

        return result;
    }

}
