package com.example.greenlightcasestudyapp.retrofit_services.network_utils;

public interface NetworkResponseCallback {

    void onSuccessResponse(String jsonData);

    void onError(int errorCode, String errorData);

}
