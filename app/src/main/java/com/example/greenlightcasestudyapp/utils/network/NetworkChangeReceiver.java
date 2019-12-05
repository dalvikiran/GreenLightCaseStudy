package com.example.greenlightcasestudyapp.utils.network;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.greenlightcasestudyapp.BuildConfig;
import com.example.greenlightcasestudyapp.activities.GreenLightAppActivity;

import java.util.List;

/**
 * Summary
 * This java file is used to broadcast the change in internet connectivity
 * this is continuous running background service
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    private Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        int status = InternetConnectionUtil.getConnectivityStatus(context);


        Log.e("Receiver ", "" + status);
        if (!isAppRunning(context, BuildConfig.APPLICATION_ID)) {
            // App is running
            //Log.e("log ", "app open" );
            GreenLightAppActivity.internetNotification(status);
        }
        if (status > 0 ) {
            //Log.e("log", "Connected");

           /* if(mContext instanceof LoginActivity || mContext instanceof ForgotOTPActivity || mContext instanceof ForgotPasswordActivity
                    || mContext instanceof DashboardActivity || mContext instanceof WelcomeActivity){
                //Code
            }else{
                Intent i = new Intent(context, BackgroundService.class);
                context.startService(i);
            }*/

        }
    }

    public static boolean isAppRunning(final Context context, final String packageName) {

        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            if (runningProcesses!= null) {

                for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        for (String activeProcess : processInfo.pkgList) {
                            if (activeProcess.equals(context.getPackageName())) {
                                isInBackground = false;
                            }
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }
}
