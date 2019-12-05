package com.example.greenlightcasestudyapp.utils;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LifecycleOwner;


public class GenericBaseObservable extends BaseObservable
{

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);
    private final SnackbarMessage mSnackbar = new SnackbarMessage();

    public SnackbarMessage getmSnackbar() {
        return mSnackbar;
    }

    private final Context mContext;

    public GenericBaseObservable(Application application, @Nullable LifecycleOwner owner, @Nullable View view) {
        super();
        this.mContext = application;
        if (owner != null)
            setupSnackbar(owner, view);
    }

    public Context getmContext() {
        return mContext;
    }

    public void setupSnackbar(LifecycleOwner owner, View view) {
        this.getmSnackbar().observe(owner, new SnackbarMessage.SnackBarObserver() {
            @Override
            public void onNewMessage(String message) {
            /** Snackbar message* */
                SnackbarUtils.showSnackbar(view, message);
            }
        });
    }
}
