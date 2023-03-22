package com.example.trashreport.DbRoomARemplacer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},exportSchema = false,version=1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DB_name="RpgMaker_users";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DB_name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract UserDao userDao();
}
