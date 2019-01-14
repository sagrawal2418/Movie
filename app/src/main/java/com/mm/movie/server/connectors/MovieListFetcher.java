package com.mm.movie.server.connectors;

import com.mm.movie.BuildConfig;
import com.mm.movie.model.Movie;
import com.mm.movie.server.RetrofitInterface;
import com.mm.movie.server.RetrofitRESTClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Reusable class to fetch movie list from server. <br/>
 * Please note that this is dependent on retrofit only. <br/>
 * This class code should be written in a way that if we need to change REST library later then only this class should need changes.
 */
public class MovieListFetcher extends BaseFetcher {
    private MovieListListener movieListListener;

    public MovieListFetcher(MovieListListener movieListListener) {
        this.movieListListener = movieListListener;
    }

    public void fetch() {
        RetrofitInterface retrofitInterface =
                RetrofitRESTClient.RESTHelper.INSTANCE.create(RetrofitInterface.class);

        Call<List<Movie>> call = retrofitInterface.getTopRatedMovies(BuildConfig.COMMIT_ID);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                notifyMovieList(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                notifyError(t.getLocalizedMessage());
            }
        });
    }

    public void notifyMovieList(List<Movie> movies) {
        if (movieListListener != null) {
            movieListListener.onMovieList(movies);
        }
    }

    public interface MovieListListener {
        void onMovieList(List<Movie> movies);
    }
}
