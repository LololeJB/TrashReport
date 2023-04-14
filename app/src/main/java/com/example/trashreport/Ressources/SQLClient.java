package com.example.trashreport.Ressources;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLClient extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=5;
    public static final String DATABASE_FILE="TrashReport.db";
    public static final String SQL_CREATE_P1="CREATE TABLE User (UserId INTEGER PRIMARY KEY AUTOINCREMENT,Email VARCHAR2(50),Password VARCHAR2(50));";
    public static final String SQL_CREATE_P2="CREATE TABLE Dechets(DechetId INTEGER PRIMARY KEY AUTOINCREMENT,Latitue DECIMAL(3,16),Longitude DECIMAL(3,16),UserId INTEGER, Categorisation VARCHAR2(50));";
    public static final String SQL_DELETE_P1="DROP TABLE IF EXISTS User";
    public static final String SQL_DELETE_P2="DROP TABLE IF EXISTS Dechets";


    public SQLClient(Context context){
        super(context,DATABASE_FILE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLClient.SQL_CREATE_P1);
        db.execSQL(SQLClient.SQL_CREATE_P2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQLClient.SQL_DELETE_P2);
        db.execSQL(SQLClient.SQL_DELETE_P1);
        this.onCreate(db);
    }
}
