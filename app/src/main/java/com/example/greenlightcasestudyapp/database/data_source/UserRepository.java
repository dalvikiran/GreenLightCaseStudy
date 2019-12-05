package com.example.greenlightcasestudyapp.database.data_source;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.greenlightcasestudyapp.database.GreenLightDatabase;
import com.example.greenlightcasestudyapp.database.IDataSourceCallback;
import com.example.greenlightcasestudyapp.models.User;
import com.example.greenlightcasestudyapp.utils.AppExecutors;

import java.util.ArrayList;


public class UserRepository extends UserDataSource {

    private volatile static UserRepository INSTANCE = null;
    private final UserDataSource userLocalDataSource;
    private final UserDataSource userRemoteDataSource;

    public UserRepository(UserDataSource userLocalDataSource,UserDataSource userRemoteDataSource) {
        this.userLocalDataSource = userLocalDataSource;
        this.userRemoteDataSource = userRemoteDataSource;
    }

    public static UserRepository getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDataSource.class) {
                if (INSTANCE == null) {
                    GreenLightDatabase database = GreenLightDatabase.getInstance(context);
                    INSTANCE = new UserRepository( UserLocalDataSource.getInstance(new AppExecutors(), database.userDao()),
                            UserRemoteDataSource.getInstance(context) );
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getUsers(@NonNull final IDataSourceCallback<ArrayList<User>> callback) {
        userLocalDataSource.getUsers(new IDataSourceCallback<ArrayList<User>>() {
            @Override
            public void onDataFound(ArrayList<User> userArrayList) {
                callback.onDataFound(userArrayList);
            }

            @Override
            public void onDataNotFound() {
                userRemoteDataSource.getUsers(new IDataSourceCallback<ArrayList<User>>() {
                    @Override
                    public void onDataFound(ArrayList<User> userArrayList) {
                        callback.onDataFound(userArrayList);
                        insertUserData(userArrayList,null);
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }

            @Override
            public void onError(String error) {
                callback.onError(error);
            }
        });

    }

    @Override
    public void insertUserData(@NonNull ArrayList<User> userArrayList, @NonNull IDataSourceCallback<String> callback) {
        userLocalDataSource.insertUserData(userArrayList, callback);
    }

    @Override
    public void deleteUserData(@NonNull User user, @NonNull IDataSourceCallback<String> callback) {
        userLocalDataSource.deleteUserData(user, callback);
    }
}
