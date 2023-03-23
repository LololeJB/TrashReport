package com.example.trashreport.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLClient extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_FILE="TrashReport.db";
    public static final String SQL_CREATE="CREATE TABLE User (UserId INTEGER PRIMARY KEY,Email VARCHAR2(50));" +
            "CREATE TABLE Dechets(DechetId INTEGER PRIMARY KEY,Latitue DECIMAL(3,16),Longitude DECIMAL(3,16),UserId FOREIGN KEY REFERENCES User(UserId);";
    public static final String SQL_DELETE="DROP TABLE IF EXISTS User , Dechets";

    public SQLClient(Context context){
        super(context,DATABASE_FILE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLClient.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLClient.SQL_DELETE);
        this.onCreate(db);
    }
}
