package com.example.greenlightcasestudyapp.database.converters;

import androidx.room.TypeConverter;
import com.example.greenlightcasestudyapp.models.Address;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class AddressConverter {

    @TypeConverter
    public static Address fromString(String value){
        Type addressDataType = new TypeToken<Address>(){}.getType();

        return new Gson().fromJson(value,addressDataType );
    }

    @TypeConverter
    public static String fromObject(Address address){
        Gson gson = new Gson();
        return gson.toJson(address);
    }
}
