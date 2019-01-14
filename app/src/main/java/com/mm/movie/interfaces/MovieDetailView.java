package com.mm.movie.interfaces;

import com.mm.movie.model.Movie;
import com.mm.movie.model.MovieDetail;

public interface MovieDetailView extends BaseView {
    void showInitialDetails(Movie movie);

    void onMovieDetail(MovieDetail movieDetail);
}
