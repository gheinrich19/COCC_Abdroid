package com.example.test_android;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by gage heinrich on 6/18/14. i am going to implement Jsoup to arse HTML into a listview. it will target the
 * table in the cocc calendar.
 */
public class library extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);



        class parsingtable extends AsyncTask<Void, Void, String> {
            @Override


            protected String doInBackground(Void... params) {
                String MyTAG = "MYTAG";
                String title = "";

                // this is a List that we will use to store info being skimmed from url
                List columnTitles = new ArrayList<String>();

                try {

                    // initialize the Document to pull data from to parse
                    Document doc = Jsoup.connect("http://events.cocc.edu/wv3/wv3_servlet/urd/run/wv_event.DayList?evdt=0,evfilter=61580").get();
                    title = doc.title();
                    Log.d(MyTAG, "" + title);

                    // ListHeading is the HTML className located on the webpage in which table we are pulling from

                    Elements tblElements = doc.getElementsByClass("ListHeading");


                    // This for loop grabs each instance of Listheading class and puts in List
                    for( Element e : tblElements){
                        String s = e.text();
                        columnTitles.add(s);
                        Log.d(MyTAG, "" + e.text());
                    }





                } catch (IOException e) {
                    e.printStackTrace();
                }

                return title;
            }

            @Override
            protected void onPostExecute(String result) {
                ((TextView)findViewById(R.id.libraryhours)).setText(result);


            }


        }
        parsingtable parsingtable = new parsingtable();
        parsingtable.execute();
    }
}
