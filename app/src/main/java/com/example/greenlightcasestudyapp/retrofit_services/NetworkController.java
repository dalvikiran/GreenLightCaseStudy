package com.example.greenlightcasestudyapp.retrofit_services;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.greenlightcasestudyapp.retrofit_services.network_utils.NetworkResponseCallback;
import com.example.greenlightcasestudyapp.retrofit_services.network_utils.NetworkUtils;
import com.example.greenlightcasestudyapp.retrofit_services.services.UserService;

import org.json.JSONObject;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkController {

    private static NetworkController instance;
    private static Context mContext;

    public static final String SERVER_ERROR = "Something went wrong on the server";


    public synchronized static NetworkController getInstance(Context context) {
        mContext = context;

        if (instance == null) {
            instance = new NetworkController();
        }
        return instance;
    }

    private class RetrofitServiceTask implements Callback<ResponseBody> {

        NetworkResponseCallback networkResponseCallback;

        public RetrofitServiceTask(NetworkResponseCallback networkResponseCallback) {
            this.networkResponseCallback = networkResponseCallback;
        }

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.code() == NetworkUtils.HTTP_SUCCESS) {
                networkResponseCallback.onSuccessResponse(NetworkUtils.getStringResponseFromRaw(response));
            } else {
                String errorMsg;
                String jsonError = NetworkUtils.getStringResponseFromRaw(response.errorBody());

                try {
                    JSONObject jsonObject = new JSONObject(jsonError);
                    errorMsg = jsonObject.optString(response.code() + "");
                } catch (Exception e) {
                    errorMsg = SERVER_ERROR;
                }
                if (errorMsg.isEmpty()) {
                    errorMsg = SERVER_ERROR;
                }

                networkResponseCallback.onError(response.code(), errorMsg);

            }
        }

        @Override
        public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
            networkResponseCallback.onError(NetworkUtils.HTTP_RETROFIT_FAILURE, t.getMessage());
        }
    }

    public void getUsers(NetworkResponseCallback callback) {
        Retrofit retrofit = NetworkUtils.buildRetrofit(true, mContext);
        UserService service = retrofit.create(UserService.class);
        Call<ResponseBody> authenticationResponseCall = service.getUsers();
        authenticationResponseCall.enqueue(new RetrofitServiceTask(callback));
    }

}
