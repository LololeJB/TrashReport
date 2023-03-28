package com.example.trashreport.Ressources;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class UserDAO {

    public static String selectPasswordbyEmail(SQLClient bdd,String email) {
        SQLiteDatabase db = bdd.getReadableDatabase();
        Cursor ReqResult = db.rawQuery("select UserId, Password from User where Email= " + email, null);
        db.close();
        String clientPassword;
        if (ReqResult.moveToFirst()) {
            clientPassword = ReqResult.getString(ReqResult.getColumnIndexOrThrow("Password"));
        } else {
            throw new NullPointerException("Pas de résultats");
        }
        return clientPassword;
    }
    public static int countAll(SQLClient bdd){
        SQLiteDatabase db = bdd.getReadableDatabase();
        Cursor ReqResult = db.rawQuery("select count(UserId) number from User", null);
        db.close();
        int allusers;
        if (ReqResult.moveToFirst()) {
            allusers = ReqResult.getInt(ReqResult.getColumnIndexOrThrow("number"));
        } else {
            return 0;
        }
        return allusers;
    }
    public static void InsertUser(SQLClient bdd,String email, String password){
        SQLiteDatabase db =bdd.getWritableDatabase();
        db.execSQL("insert into User values(countall(bdd)+1,email,password);");
        db.close();
    }
    public static int countOccurencesOfEmail(SQLClient bdd, String email){
        SQLiteDatabase db = bdd.getReadableDatabase();
        Cursor ReqResult = db.rawQuery("select count(UserId) number from User where Email = "+email, null);
        db.close();
        int allusers;
        if (ReqResult.moveToFirst()) {
            allusers = ReqResult.getInt(ReqResult.getColumnIndexOrThrow("number"));
        } else {
            return 0;
        }
        return allusers;
    }

}
