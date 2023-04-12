package com.example.trashreport.Ressources;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class UserDAO {

    public static String selectPasswordbyEmail(SQLClient bdd,String email) {
        SQLiteDatabase db = bdd.getReadableDatabase();
        Cursor ReqResult = db.rawQuery("select UserId, Password from User where Email = '"+email+"'", null);
        String clientPassword;
        if (ReqResult.moveToFirst()) {
            clientPassword = ReqResult.getString(ReqResult.getColumnIndexOrThrow("Password"));
        } else {
            throw new NullPointerException("Pas de résultats");
        }
        db.close();
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
        db.execSQL("insert into User(Email, Password) values('"+email+"','"+password+"');");
        db.close();
    }
    public static int countOccurencesOfEmail(SQLClient bdd, String email){
        SQLiteDatabase db = bdd.getReadableDatabase();
        Cursor ReqResult = db.rawQuery("select count(UserId) number from User where Email = '"+email+"'", null);
        int allusers;
        if (ReqResult.moveToFirst()) {
            allusers = ReqResult.getInt(ReqResult.getColumnIndexOrThrow("number"));
        } else {
            return 0;
        }
        db.close();
        return allusers;
    }

    public static int verifPasswordandEmail(SQLClient bdd,String email, String mdp) {
        if (countOccurencesOfEmail(bdd, email) == 1) {
            SQLiteDatabase db = bdd.getReadableDatabase();
            Cursor ReqResult = db.rawQuery("select count(UserId) number from User where Email = '" + email + "' and Password = '"+ mdp +"'", null);
            int clientPassword;
            if (ReqResult.moveToFirst()) {
                clientPassword = ReqResult.getInt(ReqResult.getColumnIndexOrThrow("number"));
            } else {
                return 0;
            }
            db.close();
            return clientPassword;
        }
        return 0;
    }
}
