package com.example.greenlightcasestudyapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @NonNull
    @PrimaryKey()
    private long id;

    @NonNull
    @ColumnInfo
    private String name;

    @NonNull
    @ColumnInfo
    private String username;

    @NonNull
    @ColumnInfo
    private String email;

    @NonNull
    @ColumnInfo
    private Address address;

    @NonNull
    @ColumnInfo
    private String phone;

    @NonNull
    @ColumnInfo
    private String website;

    @NonNull
    @ColumnInfo
    private Company company;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public Address getAddress() {
        return address;
    }

    public void setAddress(@NonNull Address address) {
        this.address = address;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getWebsite() {
        return website;
    }

    public void setWebsite(@NonNull String website) {
        this.website = website;
    }

    @NonNull
    public Company getCompany() {
        return company;
    }

    public void setCompany(@NonNull Company company) {
        this.company = company;
    }
}
