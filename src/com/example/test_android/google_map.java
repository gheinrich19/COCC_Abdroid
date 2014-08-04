package com.example.test_android;

/**
 * Created by gage heinrich on 6/12/14. This is the google map api impleminatation. It uses a
 * googlemap object which calls the getmap() funcion to actually retrieve the map data. I am using an
 * overlay of the campus map currently to show all buildings etc. and not have to create my own.
 */

import android.app.Activity;
import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.*;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import java.io.*;


public class google_map extends Activity {


    GoogleMap coccmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map);


        class readJsonToMap extends AsyncTask<Void,Void,Void>{

            StringBuilder builder = new StringBuilder();
            String line = null;
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    InputStream file = getAssets().open("bend_campus.json");

                    BufferedReader br = new BufferedReader(new InputStreamReader(file));

                    while ((line = br.readLine()) != null ){

                        Log.d("Json_tag", line);

                        builder.append(line);


                    }
                }


                catch(IOException e )
                { e.printStackTrace();}

                return null;
            }
        }

        readJsonToMap readJsonToMap = new readJsonToMap();
        readJsonToMap.execute();
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




        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(-121.3496053,44.072791 ),
                    new LatLng(-121.3496107,44.0726638),
                    new LatLng(-121.3500988,44.0726754),
                    new LatLng(-121.3501096,44.0724711),
                    new LatLng(-121.3491976,44.0724518),
                    new LatLng(-121.349203,44.0726561),
                    new LatLng(-121.3489884,44.0726522),
                    new LatLng(-121.348983,44.0727717),
                    new LatLng(-121.3496053,44.072791));


// Get back the mutable Polygon
        Polygon polygon= coccmap.addPolygon(rectOptions);
        




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





