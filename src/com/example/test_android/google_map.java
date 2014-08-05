package com.example.test_android;

/**
 * Created by gage heinrich on 6/12/14. This is the google map api impleminatation. It uses a
 * googlemap object which calls the getmap() funcion to actually retrieve the map data. I am using an
 * overlay of the campus map currently to show all buildings etc. and not have to create my own.
 */

import android.app.Activity;
import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.*;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class google_map extends Activity {


    GoogleMap coccmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map);


        class readJsonToMap extends AsyncTask<Void,Void,Void>{

           final  StringBuilder builder = new StringBuilder();
            String line = null;
           List<Object> mapShapeList = new ArrayList<Object>();

            @Override
            protected Void doInBackground(Void... params) {
                try {

                    InputStream instr = getAssets().open("bend_campus.json");
                    BufferedReader br = new BufferedReader(new InputStreamReader(instr,"UTF-8"));
                    String line = null;
                    while((line = br.readLine()) != null )
                    {
                        builder.append(line);

                    }
                    instr.close();
                    // So in order to not crash the Parser we have to escape all the newline \n and also start at the first {
                    // thats why we are using the String variable s.
                   // Log.d("parser", builder.toString());
                    String s =  builder.substring(builder.indexOf("{"));
                    s.replaceAll("\n", "\\n");
                    // create json object that calls in the whole file bend_campus.json
                    JSONObject jobj = new JSONObject(s);


                    Log.d("Jobj", jobj.toString());
                    // all shapes are listed in an array so we must call the array and loop through it!
                    JSONArray allshapes = jobj.getJSONArray("features");

                    for (int i = 0 ; i < allshapes.length(); i++){

                        Json_object mapshape = new Json_object();

                        JSONObject jsonShape = allshapes.getJSONObject(i);

                       /* String description = jsonShape.getString("desc");
                        mapshape.setDesc(description); */

                        String color = jsonShape.getString("type");
                        mapshape.setColor(color);

                        String title = jsonShape.getString("geometry");
                        mapshape.setTitle(title);

                        mapShapeList.add(mapshape);
                    }



                    Log.d("Json_obj", allshapes.length() + "" + mapShapeList.indexOf(0));
                }


                catch(IOException e )
                { e.printStackTrace();} catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }

        readJsonToMap readJsonToMap = new readJsonToMap();
        readJsonToMap.execute();
        setUpMap();
    }

    // this function includes the necessary elements in order to create your own map
    Polygon polygon;
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
        coccmap.moveCamera(CameraUpdateFactory.newLatLngZoom(cocc, 15));

        // this creates amarker on the map and has a few options i only used two.
        coccmap.addMarker(new MarkerOptions()
                .title("Central Oregon Community College")
                .position(cocc));




        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(44.072791,-121.3496053),
                    new LatLng(44.0726638,-121.3496107),
                    new LatLng(44.0726754,-121.3500988),
                    new LatLng(44.0724711,-121.3501096),
                    new LatLng(44.0724518,-121.3491976),
                    new LatLng(44.0726561,-121.349203),
                    new LatLng(44.0726522,-121.3489884),
                    new LatLng(44.0727717,-121.348983),
                    new LatLng(44.072791,-121.3496053)).strokeColor(Color.BLUE).fillColor(Color.BLUE).strokeWidth(2);




// Get back the mutable Polygon

         polygon= coccmap.addPolygon(rectOptions);

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





