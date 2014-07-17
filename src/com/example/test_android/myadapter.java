package com.example.test_android;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;


/**
 * Created by gheinrich on 7/17/2014.
 */
public class myadapter extends BaseAdapter{

    private  Context mContext;
    private int numrows = 200;
    private List<String> directoryList;


    public myadapter(Context context, List<String> columnTitles) {

        this.mContext = context;
        this.directoryList = columnTitles;

        }


    @Override
    public int getCount() {
        return this.numrows;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RowView view = null;
        if (convertView == null) {
            view = new RowView(this.mContext);
            // area for properties that are all the same
            //  view.setTextSize(18);
        } else {
            //re-use old view for quicker running
            view = (RowView) convertView;
            // this is for properties that are different for each view

        }
        if ( position % 2 == 1)
        {
            // cocc_light_blue
            view.setBackgroundColor(Color.rgb(133,170,215));
        } else{

            //COCC_dark_blue
            view.setBackgroundColor(Color.rgb(39,80,108));
            view.theTextview.setTextColor(Color.rgb( 221,238,238));
        }

        view.setTextView(directoryList.get(position));

        return view;


    }


}