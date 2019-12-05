package com.example.greenlightcasestudyapp.retrofit_services.services;


import com.example.greenlightcasestudyapp.utils.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET(Constants.USER_URL)
    Call<ResponseBody> getUsers();
}
