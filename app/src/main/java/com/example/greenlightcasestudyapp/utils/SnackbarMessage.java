package com.example.greenlightcasestudyapp.utils;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;


/**
 * A SingleLiveEvent used for Snackbar messages. Like a {@link SingleLiveEvent} but also prevents
 * null messages and uses a custom observer.
 * <p>
 * Note that only one observer is going to be notified of changes.
 */


public class SnackbarMessage extends SingleLiveEvent<String> {

    public void observe(LifecycleOwner owner, final SnackBarObserver observer) {
        super.observe(owner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s == null) {
                    return;
                }
                observer.onNewMessage(s);
            }
        });
    }


    public interface SnackBarObserver {
        /**
         * Called when there is a new message to be shown.
         *
         * @param message The new message, non-null.
         */

        void onNewMessage(String message);

    }

}
