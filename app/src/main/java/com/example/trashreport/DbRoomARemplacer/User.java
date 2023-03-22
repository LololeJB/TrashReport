package com.example.trashreport.DbRoomARemplacer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "user")
public class User {

    @ColumnInfo(name = "uid")
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name ="username")
    private String username;

    @ColumnInfo(name="password")
    private String password;

    public User(int uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }
    @Ignore

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
