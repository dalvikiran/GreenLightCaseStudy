package com.example.greenlightcasestudyapp.database.converters;

import androidx.room.TypeConverter;

import com.example.greenlightcasestudyapp.models.Address;
import com.example.greenlightcasestudyapp.models.Company;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CompanyConverter {

    @TypeConverter
    public static Company fromString(String value){
        Type companyDataType = new TypeToken<Company>(){}.getType();

        return new Gson().fromJson(value,companyDataType );
    }

    @TypeConverter
    public static String fromObject(Company company){
        Gson gson = new Gson();
        return gson.toJson(company);
    }
}
