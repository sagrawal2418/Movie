package com.mm.movie.server;

import com.mm.movie.model.Movie;
import com.mm.movie.model.MovieDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("{commitId}/top_movies.json")
    Call<List<Movie>> getTopRatedMovies(@Path("commitId") String commitId);

    @GET("{commitId}/by_id/{imdbId}.json")
    Call<MovieDetail> getMovieDetails(@Path("commitId") String commitId, @Path("imdbId") String imdbId);
}
