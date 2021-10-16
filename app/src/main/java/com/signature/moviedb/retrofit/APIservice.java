package com.signature.moviedb.retrofit;

import com.signature.moviedb.helper.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIservice {

    private static Retrofit retrofit;

    public static APIEndPoint endPoint(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(APIEndPoint.class);
    }

}
