package com.example.test_android;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.*;
import java.util.ArrayList;


import java.util.List;
import android.view.Menu;
import com.google.android.gms.maps.internal.u;
import org.w3c.dom.Text;

/**
 * Created by gheinrich on 6/27/2014.
 */

public class Directory extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory);


        final List<String> DirectoryList = new ArrayList<String>();
        final SearchView mSearchview = (SearchView) findViewById(R.id.searchView);
        final ListView list = (ListView) findViewById(R.id.listViewDirectory);

        class Datahandler extends AsyncTask<Void, Void, File> implements SearchView.OnQueryTextListener {


            @Override
            protected File doInBackground(Void... params) {

                String line = null;
                try {


                    InputStream inputStream = getAssets().open("directory.csv___jb_bak___");
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String mytag = "MYTAG";
                    while ((line = br.readLine()) != null) {


                        Log.d(mytag, line);
                        DirectoryList.add(line);


                    }


                    Directory.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Directory.this, R.layout.eventcalanderitem, DirectoryList);


                            list.setAdapter(adapter);
                            list.setTextFilterEnabled(true);
                            setupSearchview();

                        }

                        private void setupSearchview(){

                            mSearchview.setIconified(false);
                            mSearchview.setOnQueryTextListener(Datahandler.this);
                            mSearchview.setSubmitButtonEnabled(true);
                            mSearchview.setQueryHint("Search COCC");

                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();

                }

                return null;
            }


            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    list.clearTextFilter();
                } else{
                    list.setFilterText(newText.toString());
                }
                return true;
            }
        }

        Datahandler dataander = new Datahandler();
        dataander.execute();
    }

}

// test comments for git and github
