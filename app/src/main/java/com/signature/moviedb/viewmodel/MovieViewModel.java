package com.signature.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.signature.moviedb.model.Movies;
import com.signature.moviedb.model.NowPlaying;
import com.signature.moviedb.model.Popular;
import com.signature.moviedb.model.Upcoming;
import com.signature.moviedb.repositories.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance();
    }

    //==Begin of viewmodel get movie by id
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void getMovieById(String movieId){
        resultGetMovieById = repository.getMovieData(movieId);
    }
    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }
    //==End of viewmodel get movie by id

    //==Begin of viewmodel get movieelements
//    private MutableLiveData<Movies> resultGetMovieelements = new MutableLiveData<>();
//    public void getMovieelements(){
//        resultGetMovieelements = repository.getMovieDataelements();
//    }
//    public LiveData<Movies> gete
//
//    }
    //End of viewmodel get movieelements

    //==Begin of viewmodel get now playing

    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultNowPlaying(){
        return resultGetNowPlaying;
    }

    //==End of viewmodel get now playing

    //==Begin of viewmodel get upcoming

    private MutableLiveData<Upcoming> resultGetUpcoming = new MutableLiveData<>();
    public void getUpcoming() { resultGetUpcoming = repository.getUpcomingData(); }
    public LiveData<Upcoming> getResultUpcoming() { return resultGetUpcoming; }
    //== End of viewmodel get upcoming


    //==Begin of viewmodel get popular
    private MutableLiveData<Popular> resultGetPopular = new MutableLiveData<>();
    public void getPopular() { resultGetPopular = repository.getPopularData(); }
    public LiveData<Popular> getResultPopular() { return resultGetPopular; }
    //== End of viewmodel get popular
}
