package com.example.greenlightcasestudyapp.database.data_source;

import androidx.annotation.NonNull;

import com.example.greenlightcasestudyapp.database.IDataSourceCallback;
import com.example.greenlightcasestudyapp.models.User;

import java.util.ArrayList;

public abstract class UserDataSource {

    public void getUsers(@NonNull IDataSourceCallback<ArrayList<User>> callback) {}

    public void getUsersFromLocal(@NonNull IDataSourceCallback<ArrayList<User>> callback) {}

    public void insertUserData(@NonNull User user, @NonNull IDataSourceCallback<String> callback) {}

    public void insertUserData(@NonNull ArrayList<User> userArrayList, @NonNull IDataSourceCallback<String> callback) {}

    public void deleteUserData(@NonNull User user, @NonNull IDataSourceCallback<String> callback) {}

}
