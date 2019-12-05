package com.example.greenlightcasestudyapp.database.data_source;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.greenlightcasestudyapp.database.IDataSourceCallback;
import com.example.greenlightcasestudyapp.models.User;
import com.example.greenlightcasestudyapp.retrofit_services.NetworkController;
import com.example.greenlightcasestudyapp.retrofit_services.network_utils.NetworkResponseCallback;
import com.example.greenlightcasestudyapp.utils.CustomGsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class UserRemoteDataSource extends UserDataSource {

    private static UserRemoteDataSource instance;
    private static NetworkController networkController;

    private static Context mContext;

    public static UserRemoteDataSource getInstance(Context context) {
        mContext = context;
        networkController = NetworkController.getInstance(context);
        if (instance == null) {
            instance = new UserRemoteDataSource();
        }
        return instance;
    }

    public UserRemoteDataSource() {
    }

    @Override
    public void getUsers(@NonNull final IDataSourceCallback<ArrayList<User>> callback) {
        networkController.getUsers(new NetworkResponseCallback() {
            @Override
            public void onSuccessResponse(String usersJsonData) {
                try {
                    JSONArray usersJsonArray = new JSONArray(usersJsonData);
                    if (usersJsonArray.length() > 0) {

                        Gson gson = CustomGsonBuilder.getInstance().create();
                        ArrayList<User> userArrayList = gson.fromJson(usersJsonArray.toString(),
                                        new TypeToken<ArrayList<User>>() {
                                        }.getType());
                        if (userArrayList != null && userArrayList.size() > 0) {
                            callback.onDataFound(userArrayList);

                        } else {
                            callback.onDataNotFound();
                        }
                    } else {
                        callback.onDataNotFound();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(int errorCode, String errorData) {
                callback.onError(errorData);
            }
        });
    }


}
