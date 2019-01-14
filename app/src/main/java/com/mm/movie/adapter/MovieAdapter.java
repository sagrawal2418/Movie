package com.mm.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mm.movie.Constants;
import com.mm.movie.DetailActivity;
import com.mm.movie.R;
import com.mm.movie.UniversalImageLoader;
import com.mm.movie.model.Movie;
import com.mm.movie.presenter.MovieListPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private MovieListPresenter movieListPresenter;
    private ImageLoader imageLoader;

    public MovieAdapter(MovieListPresenter movieListPresenter) {
        this.movieListPresenter = movieListPresenter;
        this.imageLoader = ImageLoader.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Movie movie = movieListPresenter.getMovie(position);

        holder.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movieListPresenter.getItemCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView posterIv;
        public TextView rankTv;
        public TextView titleTv;
        public TextView yearTv;
        public TextView ratingVotesTv;
        public TextView linkTv;

        public ViewHolder(View view) {
            super(view);
            posterIv = view.findViewById(R.id.poster_iv);
            rankTv = view.findViewById(R.id.rank_tv);
            titleTv = view.findViewById(R.id.title_tv);
            yearTv = view.findViewById(R.id.year_tv);
            ratingVotesTv = view.findViewById(R.id.rating_votes_tv);
            linkTv = view.findViewById(R.id.link_tv);
        }

        public void setMovie(final Movie movie) {
            imageLoader.displayImage(movie.poster, posterIv, UniversalImageLoader.RoundedImageDIOHelper.INSTANCE);
            rankTv.setText(movie.rank + ".");
            titleTv.setText(movie.title);
            yearTv.setText("(" + movie.year + ")");
            ratingVotesTv.setText(ratingVotesTv.getResources().getString(R.string.rating) + ": " + movie.imdbRating + Constants.TEXT_SEPARATOR + ratingVotesTv.getResources().getString(R.string.votes) + ": " + movie.imdbVotes);
            linkTv.setText(movie.imdbLink);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    context.startActivity(DetailActivity.getIntent(context, movie));
                }
            });
        }
    }
}
