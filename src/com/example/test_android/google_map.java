package com.example.test_android;

/**
 * Created by gage heinrich on 6/12/14. This is the google map api impleminatation. It uses a
 * googlemap object which calls the getmap() funcion to actually retrieve the map data. I am using an
 * overlay of the campus map currently to show all buildings etc. and not have to create my own.
 */

import android.app.Activity;
import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;


public class google_map extends Activity {


    GoogleMap coccmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map);


        setUpMap();
    }

    // this function includes the necessary elements in order to create your own map

    private void setUpMap() {

        // create map fragment object by casting to googlemap form getid()

        GoogleMap coccmap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        // using the function to see if the map object is null or not
        setUpMapIfNeeded();

        // lets set the map type there are four options

        coccmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //setting the location of the opening screen view
        LatLng cocc = new LatLng(44.071991, -121.348357);


        // this moves your camera view to the location and the second perameter in .newLatLngzoom is the zoom!
        coccmap.moveCamera(CameraUpdateFactory.newLatLngZoom(cocc, 20));

        // this creates amarker on the map and has a few options i only used two.
        coccmap.addMarker(new MarkerOptions()
                .title("Central Oregon Community College")
                .position(cocc));

        GroundOverlayOptions campusOverlay = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.larger_cocc_map))
                .position(cocc, 1500f, 1500f);

        coccmap.addGroundOverlay(campusOverlay);

    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (coccmap == null) {
            coccmap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (coccmap != null) {
                // The Map is verified. It is now safe to manipulate the map.

            }
        }

    }
}





