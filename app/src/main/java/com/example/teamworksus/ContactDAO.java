package com.example.teamworksus;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface ContactDAO {
    @Insert
    public void insert(Contact... contacts);

    @Query("SELECT * FROM contact WHERE mailid= :mail")
    public Contact getContactWithId(String mail);
}
