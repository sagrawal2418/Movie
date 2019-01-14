package com.mm.movie.interfaces;

import com.mm.movie.model.Movie;

public interface ListData {
    Movie getMovie(int position);

    int getItemCount();
}
