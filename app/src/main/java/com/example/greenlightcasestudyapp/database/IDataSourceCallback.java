package com.example.greenlightcasestudyapp.database;

public interface IDataSourceCallback<T> {
    default void onDataFound(T data){}
    default void onDataNotFound(){}
    default void onDataDelete(){}
    void onError(String error);
}
