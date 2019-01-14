package com.mm.movie.presenter;

import android.view.MenuItem;

import com.mm.movie.R;
import com.mm.movie.interfaces.MovieDetailView;
import com.mm.movie.model.Movie;
import com.mm.movie.model.MovieDetail;
import com.mm.movie.server.connectors.BaseFetcher;
import com.mm.movie.server.connectors.MovieDetailFetcher;

/**
 * MovieDetailPresenter is used to process movie detail.
 * This class can be used with activity as well as fragments, only requirement is that it should implement MovieDetailView interface.
 */
public class MovieDetailPresenter implements MovieDetailFetcher.MovieDetailListener, BaseFetcher.ErrorListener {
    private MovieDetailView movieDetailView;
    private Movie movie;
    private MovieDetail movieDetail;
    private MovieDetailFetcher movieDetailFetcher;

    public MovieDetailPresenter(MovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
        this.movieDetailFetcher = new MovieDetailFetcher(this);
        this.movieDetailFetcher.setErrorListener(this);
    }

    public void fetchMovieDetail(Movie movie) {
        this.movie = movie;

        movieDetailView.showInitialDetails(movie);
        movieDetailView.showProgressDialog(movieDetailView.getContext().getString(R.string.loading_message));
        movieDetailFetcher.fetch(movie.imdbId);
    }

    @Override
    public void onMovieDetail(MovieDetail movieDetail) {
        if (movieDetailView.isFinished()) return;

        movieDetailView.dismissProgressDialog();
        this.movieDetail = movieDetail;
        movieDetailView.onMovieDetail(movieDetail);
    }

    @Override
    public void onError(String error) {
        if (movieDetailView.isFinished()) return;

        movieDetailView.dismissProgressDialog();
        movieDetailView.onError(error);
    }

    public Movie getMovie() {
        return movie;
    }

    public MovieDetail getMovieDetail() {
        return movieDetail;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                movieDetailView.finishView();
                return true;
            default:
                return false;
        }
    }
}
