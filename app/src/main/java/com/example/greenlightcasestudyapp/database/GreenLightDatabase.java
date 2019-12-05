package com.example.greenlightcasestudyapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.greenlightcasestudyapp.database.converters.AddressConverter;
import com.example.greenlightcasestudyapp.database.converters.CompanyConverter;
import com.example.greenlightcasestudyapp.database.dao.UserDao;
import com.example.greenlightcasestudyapp.models.User;

@Database(
        entities = {
                User.class
        },
        version = 1,
        exportSchema = false
    )
@TypeConverters(
        {
                AddressConverter.class,
                CompanyConverter.class
        }
    )
public abstract class GreenLightDatabase extends RoomDatabase {
    private static GreenLightDatabase INSTANCE;

    public abstract UserDao userDao();

    public static synchronized GreenLightDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, GreenLightDatabase.class, "GreenLightDatabase").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

}
