package com.mm.movie;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mm.movie.adapter.MovieAdapter;
import com.mm.movie.interfaces.MovieListView;
import com.mm.movie.presenter.MovieListPresenter;
import com.mm.movie.util.RecyclerItemDecoration;

/**
 * MainActivity is used for displaying movie list in an activity.
 */
public class MainActivity extends BaseActivity implements MovieListView {
    private MovieAdapter movieAdapter;
    private MovieListPresenter movieListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        getSupportActionBar().setTitle(R.string.app_name);

        movieListPresenter = new MovieListPresenter(this);
        movieAdapter = new MovieAdapter(movieListPresenter);

        final int horizontalSpace = (int) getResources().getDimension(R.dimen.margin_med);
        final int verticalSpace = (int) getResources().getDimension(R.dimen.margin_med_small);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new RecyclerItemDecoration(verticalSpace, horizontalSpace));
        recyclerView.setAdapter(movieAdapter);

        movieListPresenter.fetchMovieList();
    }

    @Override
    public void onListDataSetChange() {
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        super.onError(error);
    }
}
