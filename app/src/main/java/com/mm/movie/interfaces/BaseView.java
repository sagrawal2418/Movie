package com.mm.movie.interfaces;

import android.content.Context;

/**
 * This is the base of clean architecture
 */
public interface BaseView {
    boolean isFinished();

    void showProgressDialog(String message);

    void dismissProgressDialog();

    Context getContext();

    void onError(String error);

    void finishView();
}
