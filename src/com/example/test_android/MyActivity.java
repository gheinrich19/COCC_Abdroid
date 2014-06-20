/*************************************************************************
 *****               Author: Gage Heinrich                             ***
 ****   Description: This program is an app that is made for COCC      ***
 ****   There is a main page that will have all the buttons            ***
 ****   or links to other "activity pages" or web URl's.               ***
 ****   It is supossed to be more convinient for those with phones     ***
 ****   to access the bobcat web account, blackbord etc.               ***
 ****                                                                  ***
 * ***********************************************************************
**/

package com.example.test_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        // button that is 2nd from the top that goes to the map_page
        Button topButton = (Button) findViewById(R.id.button);

        // button that will be on top of the homepage app
        Button myButton = (Button) findViewById(R.id.button3);
        Button google_button = (Button) findViewById(R.id.button2);
        Button fBButton = (Button) findViewById(R.id.fbButton);
        Button LibButton = (Button) findViewById(R.id.LibraryButton);

        topButton.setOnClickListener(myhandler);
        myButton.setOnClickListener(myhandler);
        google_button.setOnClickListener(myhandler);
        fBButton.setOnClickListener(myhandler);
        LibButton.setOnClickListener(myhandler);

    }

    public View.OnClickListener myhandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.fbButton){


                Intent fbintent = new Intent(MyActivity.this, facebook_cocc.class);
                startActivity(fbintent);
            }

            if (v.getId() == R.id.button) {
                Intent mapIntent = new Intent(MyActivity.this, MyMap.class);
                MyActivity.this.startActivity(mapIntent);
            }

            if (v.getId() == R.id.button3){

                Intent web_view_login = new Intent(MyActivity.this, web_view_login.class);
                startActivity(web_view_login);


            }


            if (v.getId() == R.id.button2){
                Intent clickmap = new Intent(MyActivity.this, google_map.class);
                startActivity(clickmap);

            }

            if (v.getId() == R.id.LibraryButton){
                Intent LibraryPageIntent = new Intent(MyActivity.this, library.class);
                startActivity(LibraryPageIntent);
            }


                /*sLoginView = (WebView)findViewById(R.id.button3);
                sLoginView.getSettings().setJavaScriptEnabled(true);
                sLoginView.loadUrl("http://www.cocc.edu/student-login/");
                */

            }


    };

}




