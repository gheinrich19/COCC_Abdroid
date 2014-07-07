package com.example.test_android;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.*;
import java.util.ArrayList;


import java.util.List;

/**
 * Created by gheinrich on 6/27/2014.
 */
public class Directory extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory);

       final  List<String> DirectoryList = new ArrayList<String>();
        class Datahandler extends AsyncTask<Void, Void, File> {


            @Override
            protected File doInBackground(Void... params) {


            readTheFile rd = new readTheFile();
                String line = null;
                try{
                //rd.loadWords();
                    InputStream inputStream = getAssets().open("campusdirectory.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String mytag = "MYTAG";
                    while((line = br.readLine()) != null){


                        Log.d(mytag, line);
                        DirectoryList.add(line);

                    }



                    Directory.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Directory.this, R.layout.eventcalanderitem,DirectoryList );

                            ListView list = (ListView) findViewById(R.id.listViewDirectory);
                            list.setAdapter(adapter);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();

                }



                return null;
            }

        }

        Datahandler dataander = new Datahandler();
        dataander.execute();
    }
}
