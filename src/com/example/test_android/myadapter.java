package com.example.test_android;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gheinrich on 7/17/2014.
 */
public class myadapter extends BaseAdapter implements Filterable {

    private Context mContext;
    private int numrows = 200;
    private List<String> list = new ArrayList<String>();
    private List<String> directoryList;


    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                list = (List<String>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<String> FilteredArrayNames = new ArrayList<String>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < list.size(); i++) {
                    String dataNames = list.get(i);
                    if (dataNames.toLowerCase().startsWith(constraint.toString())) {
                        FilteredArrayNames.add(dataNames);
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());

                if (constraint.length() == 0)
                {
                    FilteredArrayNames.addAll(directoryList);
                }

                return results;

            }
        };

        return filter;
    }

    public myadapter(Context context, int eventcalanderitem, List<String> columnTitles) {

        this.mContext = context;
        this.directoryList = columnTitles;

    }


    @Override
    public int getCount() {
        return this.directoryList.size();
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

        } else {
            //re-use old view for quicker running
            view = (RowView) convertView;
            // this is for properties that are different for each view

        }
        if (position % 2 == 1) {
            // cocc_light_blue
            view.setBackgroundColor(Color.rgb(133, 170, 215));
        } else {

            //COCC_dark_blue
            view.setBackgroundColor(Color.rgb(39, 80, 108));
            view.theTextview.setTextColor(Color.rgb(221, 238, 238));
        }

        view.setTextView(directoryList.get(position));

        return view;

    }
}




