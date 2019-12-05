package com.example.greenlightcasestudyapp.activities;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.greenlightcasestudyapp.R;
import com.example.greenlightcasestudyapp.adapters.UsersDetailAdapter;
import com.example.greenlightcasestudyapp.database.data_source.UserRepository;
import com.example.greenlightcasestudyapp.databinding.ActivityUsersDetailBinding;
import com.example.greenlightcasestudyapp.models.User;
import com.example.greenlightcasestudyapp.utils.SnackbarUtils;
import com.example.greenlightcasestudyapp.utils.Utils;
import com.example.greenlightcasestudyapp.view_models.UsersDetailViewModel;

import java.util.ArrayList;

public class UsersDetailActivity extends GreenLightAppActivity {

    GreenLightAppActivity greenLightAppActivity;

    ActivityUsersDetailBinding activityUsersDetailBinding;
    UsersDetailViewModel usersDetailViewModel;
    UsersDetailAdapter usersDetailAdapter;
    ArrayList<User> usersArrayList = new ArrayList<>();

    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_users_detail);

        //setting the generic child activity for notifying the internet connection
        greenLightAppActivity = this;
        setChildActivity(greenLightAppActivity);

        userRepository = UserRepository.getInstance(this);
        bindViewModel();

    }

    @Override
    public void notifyInternet(int state) {
        SnackbarUtils.notifyInternet(state, this, activityUsersDetailBinding.getRoot());

        if (usersDetailAdapter != null ){
            if (state > 0)
                usersDetailAdapter.setConnectionFlag(true);
            else
                usersDetailAdapter.setConnectionFlag(false);
        }
    }

    private void bindViewModel() {

        activityUsersDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_users_detail);

        RecyclerView recyclerView = activityUsersDetailBinding.usersRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);

        usersDetailViewModel = Utils.obtainBaseObservable(this, UsersDetailViewModel.class, this, activityUsersDetailBinding.getRoot());
//        usersDetailViewModel = new UsersDetailViewModel(this, userRepository);
        activityUsersDetailBinding.setUserViewModel(usersDetailViewModel);

        usersDetailAdapter = new UsersDetailAdapter(this,userRepository);
        recyclerView.setAdapter(usersDetailAdapter);

        setAdapterData();

        usersDetailViewModel.getUsers();
    }

    private void setAdapterData() {

        usersDetailViewModel.usersLiveData.observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> usersList) {
                usersArrayList = usersList;
                usersDetailAdapter.setUsersList(usersArrayList);
            }
        });
    }
}
