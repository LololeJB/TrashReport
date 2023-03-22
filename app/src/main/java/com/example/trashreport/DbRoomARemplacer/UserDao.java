package com.example.trashreport.DbRoomARemplacer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT username FROM user")
    List<String> getAll();

    @Query("SELECT password FROM user WHERE username=:userId")
    String getPasswordFromId(String userId);

    @Query("SELECT uid FROM user WHERE username LIKE :username")
    int getIdFromName(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    public void nukeUserTable();

}

