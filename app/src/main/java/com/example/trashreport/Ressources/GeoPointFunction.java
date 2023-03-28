package com.example.trashreport.Ressources;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

public class GeoPointFunction {
    public OverlayItem generateGeoPoint(String Title,String subtitle,double latitude, double longitude){
            return new OverlayItem(Title,subtitle,new GeoPoint(latitude,longitude));
    }
}
