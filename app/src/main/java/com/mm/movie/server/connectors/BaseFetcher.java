package com.mm.movie.server.connectors;

/**
 * Every class which is going to communicate with server should extend from BaseFetcher.
 */
public class BaseFetcher {
    private ErrorListener errorListener;

    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    protected void notifyError(String error) {
        if (errorListener != null) {
            errorListener.onError(error);
        }
    }

    public interface ErrorListener {
        void onError(String error);
    }
}
