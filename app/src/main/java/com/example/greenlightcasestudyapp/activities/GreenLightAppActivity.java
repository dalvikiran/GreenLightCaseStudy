package com.example.greenlightcasestudyapp.activities;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greenlightcasestudyapp.utils.Constants;
import com.example.greenlightcasestudyapp.utils.network.ChangeListener;
import com.example.greenlightcasestudyapp.utils.network.NetworkChangeReceiver;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public abstract class GreenLightAppActivity extends AppCompatActivity implements ChangeListener.listener  {

    IntentFilter intentFilter;
    static Context context;
    GreenLightAppActivity activity;
    static int internetState;
    private static boolean state = false;
    private static ChangeListener listener;
    NetworkChangeReceiver receiver;


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        context = GreenLightAppActivity.this;
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        listener = new ChangeListener(state);
        listener.setChangeListener(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }


    //Handle the internet connection change scenario
    public static void internetNotification(int log) {
        internetState = log;
        if (log > 0) {
            state = true;
            //checkNewLeadLocalData(context);
            //checkApplicantDetailData(context);

        } else {
            Constants.internetNotConnected = true;
            state = false;
        }
        listener.somethingChanged();
    }

    //this method provides which activity is currently in fore ground
    public void setChildActivity(GreenLightAppActivity activity) {
        this.activity = activity;
    }

    // to notify the change of connection
    public abstract void notifyInternet(int state);


    @Override
    public void onChange(boolean b) {
        activity.notifyInternet(internetState);
    }


    private Handler disconnectHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // todo
//            Toast.makeText(ShareKhanLoansActivity.this,"disconnectHandler", Toast.LENGTH_SHORT).show();
            return true;
        }
    });

    private Runnable disconnectCallback = new Runnable() {
        @Override
        public void run() {
            // Perform any required operation on disconnect
            //Toast.makeText(ShareKhanLoansActivity.this,"Time Out",Toast.LENGTH_LONG).show();
            /*sharedPreferences.edit().clear().apply();
            ActivityCompat.finishAffinity(ShareKhanLoansActivity.this);
            Intent intent = new Intent(ShareKhanLoansActivity.this, LoginActivity.class);
            startActivity(intent);*/
        }
    };

    //    in-activity timer reset
    public void resetDisconnectTimer() {
        disconnectHandler.removeCallbacks(disconnectCallback);
        disconnectHandler.postDelayed(disconnectCallback, Constants.DISCONNECT_TIMEOUT);
    }

    public void stopDisconnectTimer() {
        disconnectHandler.removeCallbacks(disconnectCallback);
    }

    @Override
    public void onUserInteraction() {
        resetDisconnectTimer();
    }

    @Override
    public void onResume() {
        super.onResume();
        //resetDisconnectTimer();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopDisconnectTimer();
    }

}
