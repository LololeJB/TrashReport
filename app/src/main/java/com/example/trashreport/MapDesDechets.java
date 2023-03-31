package com.example.trashreport;

import static com.example.trashreport.Ressources.GeoPointsDAO.getAllGeoPoints;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.Map;

public class MapDesDechets extends AppCompatActivity {

    private Button submitplace;
    private MapView MapDesDechets;
    Context context;
    private IMapController MapController;
    private GeoPoint startpoint = null;
    ArrayList<OverlayItem> geopoints = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapdesdechets);
        context = getApplicationContext();
        MapDesDechets = findViewById(R.id.mapdesdechets_mapview);
        submitplace = findViewById(R.id.mapdesdechets_signalerdecharge);
        androidUpdateLocation();

        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));
        MapDesDechets.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        MapDesDechets.setMultiTouchControls(true);
        //(a modifier) centrer la map sur l'iut
        startpoint=new GeoPoint(43.570127979080205,1.4650829805386556);
        MapController = MapDesDechets.getController();
        MapController.setZoom(18.0);
        MapController.setCenter(startpoint);
        //geopoints = getAllGeoPoints();



        //bouton pour submit un déchet
        submitplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signalementdesdechet = new Intent(MapDesDechets.this, SignalementDesDechets.class);
                startActivity(signalementdesdechet);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        MapDesDechets.onPause();
        if (androidLocationListener != null) {
            if (androidLocationManager == null) {
                androidLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            }
            androidLocationManager.removeUpdates(androidLocationListener);
            androidLocationManager = null;
            androidLocationListener = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MapDesDechets.onResume();
    }

    private LocationManager androidLocationManager;
    private LocationListener androidLocationListener;
    private final static int REQUEST_CODE_UPDATE_LOCATION=42;

    public void androidUpdateLocation(){
        if (ActivityCompat.checkSelfPermission(MapDesDechets.this, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MapDesDechets.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_UPDATE_LOCATION);
        } else {
            androidLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            androidLocationListener = new LocationListener() {
                public void onLocationChanged(Location loc) {
                    Toast.makeText(MapDesDechets.this, "Vous bougez, mois vous êtes ici : "+loc.getLatitude()+" / "+loc.getLongitude(), Toast.LENGTH_LONG).show();
                }
                public void onStatusChanged(String provider, int status, Bundle extras) {}
                public void onProviderEnabled(String provider) {}
                public void onProviderDisabled(String provider) {}
            };

            androidLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000, // en millisecondes
                    50, // en mètres
                    androidLocationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_UPDATE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    androidUpdateLocation();
                } else {
                    Toast.makeText(MapDesDechets.this, "Permission refusée.", Toast.LENGTH_LONG).show();
                }
                return;
        }
    }

}
