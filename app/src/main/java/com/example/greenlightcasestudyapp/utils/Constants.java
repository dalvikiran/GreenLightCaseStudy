package com.example.greenlightcasestudyapp.utils;

public class Constants {

    public static final String SERVER_URL = "https://jsonplaceholder.typicode.com";

    public static final String USER_URL = "users";

    //network connectivity
    public static final String CONNECT_TO_WIFI = "WIFI";
    public static final String CONNECT_TO_MOBILE = "MOBILE";
    public static final String NOT_CONNECT = "NOT_CONNECT";
    public final static String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    public static final String INTERNET_CONNECTED = "Internet Connected";
    public static final String INTERNET_DISCONNECTED = "Internet Disconnected";

    //internet connection
    public static boolean internetNotConnected = false;
    public static final long DISCONNECT_TIMEOUT = 30 * 60 * 1000; // 30 min = 30 * 60 * 1000 ms
    public static final int SNACKBAR_HEIGHT = 100; // 4 sec

}
