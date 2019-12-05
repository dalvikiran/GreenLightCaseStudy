package com.example.greenlightcasestudyapp.view_models;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;

import com.example.greenlightcasestudyapp.models.User;
import com.example.greenlightcasestudyapp.utils.SingleLiveEvent;

public class UserListItemViewModel extends BaseObservable {

    public User user;
    public ObservableBoolean deleteVisibility = new ObservableBoolean(false);
    public SingleLiveEvent<Void> onDeleteClickEvent = new SingleLiveEvent<>();


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ObservableBoolean getDeleteVisibility() {
        return deleteVisibility;
    }

    public void setDeleteVisibility(ObservableBoolean deleteVisibility) {
        this.deleteVisibility = deleteVisibility;
    }

    public void onDeleteClick(){
        onDeleteClickEvent.call();
    }

    public SingleLiveEvent<Void> getOnDeleteClickEvent() {
        return onDeleteClickEvent;
    }
}
