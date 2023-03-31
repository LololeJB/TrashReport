package com.example.trashreport;

import static com.example.trashreport.Ressources.GeoPointsDAO.getAllGeoPoints;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.trashreport.Ressources.SQLClient;

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
    SQLClient bdd;
    ArrayList<OverlayItem> geopoints = new ArrayList<>();
    Location lavraieLocation = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapdesdechets);
        context = getApplicationContext();
        MapDesDechets = findViewById(R.id.mapdesdechets_mapview);
        submitplace = findViewById(R.id.mapdesdechets_signalerdecharge);
        bdd = new SQLClient(this);
        androidUpdateLocation();
        getLastLocation();

        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));
        MapDesDechets.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        MapDesDechets.setMultiTouchControls(true);
        //(a modifier) centrer la map sur l'iut
        startpoint = new GeoPoint(lavraieLocation.getLatitude(), lavraieLocation.getLongitude());
        MapController = MapDesDechets.getController();
        MapController.setZoom(18.0);
        MapController.setCenter(startpoint);
        try{
            geopoints = getAllGeoPoints(bdd);
            ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(), geopoints, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                @Override
                public boolean onItemSingleTapUp(int index, OverlayItem item) {
                    return true;
                }

                @Override
                public boolean onItemLongPress(int index, OverlayItem item) {
                    return false;
                }
            });
            mOverlay.setFocusItemsOnTap(true);
            MapDesDechets.getOverlays().add(mOverlay);
        }catch(Exception e){
            //rien à ajouter
        }

        //bouton pour submit un déchet
        submitplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidUpdateLocation();
                getLastLocation();
                Intent signalementdesdechet = new Intent(MapDesDechets.this, SignalementDesDechets.class);
                signalementdesdechet.putExtra("latitude",lavraieLocation.getLatitude());
                signalementdesdechet.putExtra("longitude",lavraieLocation.getLongitude());
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
    private final static int REQUEST_CODE_UPDATE_LOCATION = 42;

    public void androidUpdateLocation() {
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
                    lavraieLocation = extractLocation(loc);
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };

            androidLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000, // en millisecondes
                    50, // en mètres
                    androidLocationListener);
        }
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(MapDesDechets.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(MapDesDechets.this,Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(MapDesDechets.this, "Permission nécessaire avait déjà été refusée.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MapDesDechets.this, "Demande de permission lancée.", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(
                        MapDesDechets.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        } else {
            androidLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            Location loc = androidLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(loc==null) {
                Toast.makeText(MapDesDechets.this, "Pas de localisation disponible", Toast.LENGTH_LONG).show();
            } else {
                lavraieLocation = extractLocation(loc);
            }
        }
    }
    private Location extractLocation(Location loc){
        return loc;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.quitmenu:
                Intent connexion = new Intent(MapDesDechets.this,MainActivity.class);
                startActivity(connexion);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

}
