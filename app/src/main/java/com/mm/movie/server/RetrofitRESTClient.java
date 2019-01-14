package com.mm.movie.server;

import com.mm.movie.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRESTClient {
    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static class RESTHelper {
        public static Retrofit INSTANCE = getClient();
    }
}
