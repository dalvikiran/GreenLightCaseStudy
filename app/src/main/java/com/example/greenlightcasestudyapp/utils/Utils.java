package com.example.greenlightcasestudyapp.utils;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import com.example.greenlightcasestudyapp.database.data_source.UserRepository;
import com.example.greenlightcasestudyapp.view_models.UsersDetailViewModel;

public class Utils {

    public static <T extends GenericBaseObservable> T obtainBaseObservable(AppCompatActivity activity, Class<T> modelClass, LifecycleOwner owner, View view) {

        if (modelClass.isAssignableFrom(UsersDetailViewModel.class)) {
            return (T) new UsersDetailViewModel(activity, owner, view, UserRepository.getInstance(activity.getApplication()));
        }

        return null;
    }



}
