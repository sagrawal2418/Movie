package com.mm.movie.server.connectors;

import com.mm.movie.BuildConfig;
import com.mm.movie.model.MovieDetail;
import com.mm.movie.server.RetrofitInterface;
import com.mm.movie.server.RetrofitRESTClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Reusable class to fetch movie detail from server.
 */
public class MovieDetailFetcher extends BaseFetcher {
    private MovieDetailListener movieDetailListener;

    public MovieDetailFetcher(MovieDetailListener movieDetailListener) {
        this.movieDetailListener = movieDetailListener;
    }

    public void fetch(String imdbId) {
        RetrofitInterface retrofitInterface =
                RetrofitRESTClient.RESTHelper.INSTANCE.create(RetrofitInterface.class);

        Call<MovieDetail> call = retrofitInterface.getMovieDetails(BuildConfig.COMMIT_ID, imdbId);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                notifyMovieDetail(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                notifyError(t.getLocalizedMessage());
            }
        });
    }

    public void notifyMovieDetail(MovieDetail movieDetail) {
        if (movieDetailListener != null) {
            movieDetailListener.onMovieDetail(movieDetail);
        }
    }

    public interface MovieDetailListener {
        void onMovieDetail(MovieDetail movieDetail);
    }
}
