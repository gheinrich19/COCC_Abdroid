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
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private WebView sLoginView;
    @Override


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        // button that is 2nd from the top that goes to the map_page
        Button topButton = (Button) findViewById(R.id.button);

        // button that will be on top of the homepage app
        Button myButton = (Button) findViewById(R.id.button3);

        Button google_button = (Button) findViewById(R.id.button2);


        topButton.setOnClickListener(myhandler);
        myButton.setOnClickListener(myhandler);
        google_button.setOnClickListener(myhandler);

    }

    public View.OnClickListener myhandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.button) {
                Intent mapIntent = new Intent(MyActivity.this, MyMap.class);
                MyActivity.this.startActivity(mapIntent);
            }

            if (v.getId() == R.id.button3){

                Intent web_view_login = new Intent(MyActivity.this, web_view_login.class);
                startActivity(web_view_login);

               /* String slogin = "http://www.cocc.edu/student-login/";
                Intent gotopage = new Intent(Intent.ACTION_VIEW);
                gotopage.setData(Uri.parse(slogin));
                startActivity(gotopage);
                */
            }


            if (v.getId() == R.id.button2){
                Intent clickmap = new Intent(MyActivity.this, google_map.class);
                startActivity(clickmap);



            }
                /*sLoginView = (WebView)findViewById(R.id.button3);
                sLoginView.getSettings().setJavaScriptEnabled(true);
                sLoginView.loadUrl("http://www.cocc.edu/student-login/");
                */

            }


    };

}




