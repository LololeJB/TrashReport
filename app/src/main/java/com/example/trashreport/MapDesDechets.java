package com.example.trashreport;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
    ArrayList<OverlayItem> geopoints = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapdesdechets);
        context=getApplicationContext();
        MapDesDechets=findViewById(R.id.mapdesdechets_mapview);

        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));
        MapDesDechets.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        MapDesDechets.setMultiTouchControls(true);

        GeoPoint startpoint = new GeoPoint(43.570127979080205, 1.4650829805386556);
        MapController = MapDesDechets.getController();
        MapController.setZoom(18.0);
        MapController.setCenter(startpoint);
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
    }
    @Override
    public void onPause(){
        super.onPause();
        MapDesDechets.onPause();
    }
    @Override
    public void onResume(){
        super.onResume();
        MapDesDechets.onResume();
    }
}
