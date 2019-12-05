package com.example.greenlightcasestudyapp.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.greenlightcasestudyapp.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<User> users);

    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Delete
    void delete(User user);
}
