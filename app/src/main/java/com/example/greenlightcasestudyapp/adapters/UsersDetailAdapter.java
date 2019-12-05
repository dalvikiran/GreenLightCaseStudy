
package com.example.greenlightcasestudyapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.example.greenlightcasestudyapp.R;
import com.example.greenlightcasestudyapp.activities.UsersDetailActivity;
import com.example.greenlightcasestudyapp.database.IDataSourceCallback;
import com.example.greenlightcasestudyapp.database.data_source.UserRepository;
import com.example.greenlightcasestudyapp.databinding.UserCellLayoutBinding;
import com.example.greenlightcasestudyapp.models.User;
import com.example.greenlightcasestudyapp.utils.SnackbarUtils;
import com.example.greenlightcasestudyapp.view_models.UserListItemViewModel;

import java.util.ArrayList;

public class UsersDetailAdapter extends RecyclerView.Adapter<UsersDetailAdapter.UserViewHolder> {

    private ArrayList<User> userArrayList = new ArrayList<>();
    private boolean isInternetConnected = true;
    UserRepository userRepository;
    Activity activity;
    LifecycleOwner owner;

    public UsersDetailAdapter(LifecycleOwner owner,UserRepository userRepository) {
        this.userRepository = userRepository;
        this.owner = owner;
    }

    public void setUsersList(ArrayList<User> usersList) {
        userArrayList = usersList;
        notifyDataSetChanged();
    }

    public void setConnectionFlag(boolean isConnected){
        isInternetConnected = isConnected;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserCellLayoutBinding userCellLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.user_cell_layout, parent, false);
        return new UserViewHolder(userCellLayoutBinding.getRoot());
    }

    @Override
    public void onViewAttachedToWindow(UserViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(UserViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User user = userArrayList.get(position);
        if (user != null){
            UserListItemViewModel userListItemViewModel = new UserListItemViewModel();
            userListItemViewModel.setUser(user);
            userListItemViewModel.getDeleteVisibility().set(!isInternetConnected);
            holder.setViewModel(userListItemViewModel);

            userListItemViewModel.onDeleteClickEvent.observeChange(owner, new Observer<Void>() {
                @Override
                public void onChanged(Void aVoid) {
                    deleteUser(user,holder.userCellLayoutBinding.getRoot());
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {


        public UserCellLayoutBinding userCellLayoutBinding;

        UserViewHolder(View itemView) {
            super(itemView);
            bind();
        }

         void bind() {
            if (userCellLayoutBinding == null) {
                userCellLayoutBinding = DataBindingUtil.bind(itemView);
            } else {

            }
        }

         void unbind() {
            if (userCellLayoutBinding != null) {
                userCellLayoutBinding.unbind(); // Don't forget to unbind
            }
        }

         void setViewModel(UserListItemViewModel model) {
            if (userCellLayoutBinding != null) {
                userCellLayoutBinding.setUserViewModel(model);
            }
        }

    }

    private void deleteUser(User user, View view){
        userRepository.deleteUserData(user, new IDataSourceCallback<String>() {
            @Override
            public void onDataDelete() {
                userArrayList.remove(user);
                notifyDataSetChanged();
                Toast.makeText(view.getContext(), user.getName() + "deleted", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(String error) {
//                SnackbarUtils.showSnackbar(view,error);
                Toast.makeText(view.getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}




