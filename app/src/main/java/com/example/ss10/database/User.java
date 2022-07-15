package com.example.ss10.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "username")
    public String username;
    @ColumnInfo(name = "gender")
    public String gender;
    @ColumnInfo(name = "des")
    public String des;

}
