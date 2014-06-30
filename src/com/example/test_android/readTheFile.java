package com.example.test_android;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gheinrich on 6/30/2014.
 */
public class readTheFile {



/*

    String TAG = null;
    Context mhelpercontext;
    int count = 0;
    final List<String> mylist = new ArrayList<String>();

    public void loadWords() throws IOException {
        Log.d(TAG, "Loading words...");
        final Resources resources = mhelpercontext.getResources();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = TextUtils.split(line, "-");
                if (strings.length < 2) continue;
                mylist.add(strings[count]);
                if (strings[count].length() < 0) {
                    Log.e(TAG, "unable to add word: " + strings[0].trim());
                }
                count++;
            }
        } finally {
            reader.close();
        }
        Log.d(TAG, "DONE loading words.");

    }
} */
}
