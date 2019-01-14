package com.mm.movie;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mm.movie.interfaces.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Base of all activities with all basic methods. This class also implements BaseView so that we don't need to implement basic methods in all activities.
 * If we are going to create fragment based architecture then BaseView should be implemented in BaseFragment in similar manner.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private static final List<Activity> ACTIVITIES = new ArrayList<>();

    private boolean finished = false;

    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;

    public static void finishAll() {
        for (Activity activity : ACTIVITIES) {
            activity.finish();
        }

        if (BuildConfig.DEBUG) Log.i(TAG, "All activities finish done.");
    }

    public static boolean areAllFinished() {
        return ACTIVITIES.size() == 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        finished = false;
        ACTIVITIES.add(this);

        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ACTIVITIES.remove(this);
        if (null != progressDialog) {
            progressDialog.cancel();
            progressDialog = null;
        }
        finished = true;
        //Remove the progress dialog if shown to prevent exception
        dismissProgressDialog();
        dismissOKDialog();

        if (BuildConfig.DEBUG) Log.d(TAG, "onDestroy");
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void showProgressDialog(String message) {
        if (null == progressDialog) {
            showNewProgressDialog(message);

            if (BuildConfig.DEBUG) Log.d(TAG, "New instance of dialog is created shown.");
        } else {
            progressDialog.setMessage(message);
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
            if (BuildConfig.DEBUG) Log.d(TAG, "Old instance of progress dialog is utilised.");
        }
    }

    private void showNewProgressDialog(String message) {
        progressDialog = ProgressDialog.show(this, null, message, true, false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void dismissProgressDialog() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onError(String error) {
        showOkDialog(error);
    }

    protected void showOkDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton(
                R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertDialog = builder.create();
        alertDialog.show();
    }

    private void dismissOKDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void finishView() {
        //in case of fragment this will be pop
        finish();
    }
}
