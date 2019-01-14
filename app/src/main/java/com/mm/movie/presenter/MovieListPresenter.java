package com.mm.movie.presenter;

import com.mm.movie.R;
import com.mm.movie.interfaces.ListData;
import com.mm.movie.interfaces.MovieListView;
import com.mm.movie.model.Movie;
import com.mm.movie.server.connectors.BaseFetcher;
import com.mm.movie.server.connectors.MovieListFetcher;

import java.util.List;

public class MovieListPresenter implements ListData, MovieListFetcher.MovieListListener, BaseFetcher.ErrorListener {
    private MovieListView movieListView;

    private List<Movie> movies;
    private MovieListFetcher movieListFetcher;

    public MovieListPresenter(MovieListView movieListView) {
        this.movieListView = movieListView;
        this.movieListFetcher = new MovieListFetcher(this);
        this.movieListFetcher.setErrorListener(this);
    }

    public void fetchMovieList() {
        movieListView.showProgressDialog(movieListView.getContext().getString(R.string.loading_message));
        movieListFetcher.fetch();
    }

    @Override
    public Movie getMovie(int position) {
        return movies.get(position);
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    @Override
    public void onMovieList(List<Movie> movies) {
        if (movieListView.isFinished()) return;

        movieListView.dismissProgressDialog();
        this.movies = movies;
        movieListView.onListDataSetChange();
    }

    @Override
    public void onError(String error) {
        if (movieListView.isFinished()) return;

        movieListView.dismissProgressDialog();
        movieListView.onError(error);
    }
}
