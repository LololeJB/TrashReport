package com.example.trashreport.Ressources;

import android.database.sqlite.SQLiteDatabase;

import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

public class GeoPointsDAO {

    public static void insertGeoPoint(SQLClient bdd, double latitude, double longitude,int userid, String categorisation){
        SQLiteDatabase db= bdd.getWritableDatabase();
        db.execSQL("Insert Into Dechets(Latitude, Longitude, UserId, Categorisation)values('"+latitude+"','"+longitude+"','"+userid+"','"+categorisation+"')");
        db.close();

    }
    public static ArrayList<OverlayItem> getAllGeoPoints(){
        return null;
    }
}
