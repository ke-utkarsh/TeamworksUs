package com.example.teamworksus;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "contact")
public class Contact {
    private String name;
    private String password;
    @PrimaryKey
    @NonNull
    private String mailid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    public String getMailid() {
        return mailid;
    }

    public void setMailid(@NonNull String mailid) {
        this.mailid = mailid;
    }
}

