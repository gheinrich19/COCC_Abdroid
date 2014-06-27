package com.example.test_android;

import android.app.Activity;
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

        class Datahandler extends AsyncTask<Void, Void, File> {


            @Override
            protected File doInBackground(Void... params) {


                try {
                    String MY_TAG = "MY_TAG";
                    InputStream inputstream = getResources().openRawResource(R.raw.campusdirectory);
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputstream));
                    String[] completeRow = new String[100];


                    String rowData;
                    int count = 0;
                    final List<String> rowList = new ArrayList<String>();
                    while ((rowData = br.readLine()) != null) ;

                    {
                        completeRow = rowData.split(",");

                        rowList.add(completeRow[0] + "  " + completeRow[1] + "  " + completeRow[2] + "  " + completeRow[3] + "  " + completeRow[4]);
                        Log.d(MY_TAG, String.valueOf(rowList.get(count)));
                        count++;
                    }

                    br.close();
                    Directory.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Directory.this, R.layout.eventcalanderitem, rowList);

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
