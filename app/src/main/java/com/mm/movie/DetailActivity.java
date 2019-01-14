package com.mm.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.mm.movie.interfaces.MovieDetailView;
import com.mm.movie.model.Movie;
import com.mm.movie.model.MovieDetail;
import com.mm.movie.presenter.MovieDetailPresenter;
import com.mm.movie.util.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Detail activity is used to display movie detail. <br/>
 * Please note that this only contains setter and getter methods.  <br/>
 * Do not put any logic in it.  <br/>
 * All the logic should be written in presenter or preferably in separate class.
 */
public class DetailActivity extends BaseActivity implements MovieDetailView {
    private ImageView posterIv;
    private TextView titleTv;
    private TextView rankTv;
    private TextView yearTv;
    private TextView ratingVotesMetascoreTv;
    private TextView plotTv;
    private TextView ratedTv;
    private TextView releasedTv;
    private TextView runtimeTv;
    private TextView genreTv;
    private TextView directorTv;
    private TextView writerTv;
    private TextView actorsTv;
    private TextView languageTv;
    private TextView countryTv;
    private TextView awardsTv;

    private MovieDetailPresenter movieDetailPresenter;

    public static Intent getIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constants.MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Movie movie = getIntent().getParcelableExtra(Constants.MOVIE);
        getSupportActionBar().setTitle(movie.title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movieDetailPresenter = new MovieDetailPresenter(this);

        initViews();

        movieDetailPresenter.fetchMovieDetail(movie);
    }

    private void initViews() {
        posterIv = findViewById(R.id.poster_iv);
        titleTv = findViewById(R.id.title_tv);
        rankTv = findViewById(R.id.rank_tv);
        yearTv = findViewById(R.id.year_tv);
        ratingVotesMetascoreTv = findViewById(R.id.rating_votes_metascore_tv);
        plotTv = findViewById(R.id.plot_tv);
        ratedTv = findViewById(R.id.rated_tv);
        releasedTv = findViewById(R.id.released_tv);
        runtimeTv = findViewById(R.id.runtime_tv);
        genreTv = findViewById(R.id.genre_tv);
        directorTv = findViewById(R.id.director_tv);
        writerTv = findViewById(R.id.writer_tv);
        actorsTv = findViewById(R.id.actors_tv);
        languageTv = findViewById(R.id.language_tv);
        countryTv = findViewById(R.id.country_tv);
        awardsTv = findViewById(R.id.awards_tv);
    }

    @Override
    public void showInitialDetails(Movie movie) {
        ImageLoader.getInstance().displayImage(movie.poster, posterIv, UniversalImageLoader.ImageDIOHelper.INSTANCE);
        titleTv.setText(movie.title);
        yearTv.setText("(" + movie.year + ")");
        ratingVotesMetascoreTv.setText(ratingVotesMetascoreTv.getResources().getString(R.string.rating) + ": " + movie.imdbRating + Constants.TEXT_SEPARATOR + ratingVotesMetascoreTv.getResources().getString(R.string.votes) + ": " + movie.imdbVotes);
        rankTv.setText(Integer.toString(movie.rank));
    }

    @Override
    public void onMovieDetail(MovieDetail movieDetail) {
        ImageLoader.getInstance().displayImage(movieDetail.poster, posterIv, UniversalImageLoader.ImageDIOHelper.INSTANCE);
        titleTv.setText(movieDetail.title);
        rankTv.setText(Integer.toString(movieDetailPresenter.getMovie().rank));
        yearTv.setText("(" + movieDetail.year + ")");
        ratingVotesMetascoreTv.setText(getString(R.string.rating) + ": " + movieDetail.imdbRating + Constants.TEXT_SEPARATOR + getString(R.string.votes) + ": " + movieDetail.imdbVotes + Constants.TEXT_SEPARATOR + getString(R.string.metascore) + ": " + movieDetail.metascore);
        plotTv.setText(movieDetail.plot);
        ratedTv.setText(movieDetail.rated);
        releasedTv.setText(movieDetail.released);
        runtimeTv.setText(movieDetail.runtime);
        genreTv.setText(StringUtils.join(movieDetail.genre));
        directorTv.setText(movieDetail.director);
        writerTv.setText(movieDetail.writer);
        actorsTv.setText(StringUtils.join(movieDetail.actors));
        languageTv.setText(StringUtils.join(movieDetail.language));
        countryTv.setText(movieDetail.country);
        awardsTv.setText(movieDetail.awards);
    }

    @Override
    public void onError(String error) {
        super.onError(error);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return movieDetailPresenter.onOptionsItemSelected(item);
    }
}
