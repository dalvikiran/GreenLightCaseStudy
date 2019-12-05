package com.example.greenlightcasestudyapp.view_models;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenlightcasestudyapp.database.IDataSourceCallback;
import com.example.greenlightcasestudyapp.database.data_source.UserRepository;
import com.example.greenlightcasestudyapp.models.User;
import com.example.greenlightcasestudyapp.utils.GenericBaseObservable;
import com.example.greenlightcasestudyapp.utils.SnackbarMessage;
import com.example.greenlightcasestudyapp.utils.SnackbarUtils;

import java.util.ArrayList;

public class UsersDetailViewModel extends GenericBaseObservable {

    private UserRepository userRepository;
    public Context mContext;
    public Activity mActivity;

    public MutableLiveData<ArrayList<User>> usersLiveData = new MutableLiveData<>();

    public UsersDetailViewModel(@NonNull Activity mActivity, @Nullable LifecycleOwner owner, @Nullable View view, UserRepository userRepository) {
        super(mActivity.getApplication(), owner, view);

        mContext = mActivity.getApplicationContext();
        this.mActivity = mActivity;
        this.userRepository = userRepository;

    }

    public void getUsers(){

        userRepository.getUsers(new IDataSourceCallback<ArrayList<User>>() {

            @Override
            public void onDataFound(ArrayList<User> userArrayList) {
                usersLiveData.setValue(userArrayList);
            }

            @Override
            public void onDataNotFound() {
                getmSnackbar().setValue("No Users Founds");
            }

            @Override
            public void onError(String error) {
                getmSnackbar().setValue(error);
            }
        });
    }

}
