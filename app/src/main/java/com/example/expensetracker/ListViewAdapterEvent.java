package com.example.expensetracker;

/**
 * Created by yueweiyang on 3/27/17.
 */

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;



import classObject.*;


class ListViewAdapterEvent extends BaseAdapter {
    //public ArrayList<HashMap<String, String>> list;
    private ArrayList<Event> list;
    private Activity activity;
    private TextView txtFirst;


    ListViewAdapterEvent(Activity activity, ArrayList<Event> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.activity_listview, parent,false);

            txtFirst=(TextView) convertView.findViewById(R.id.nameview);


        }

        Event temp = list.get(position);
        txtFirst.setText(temp.getName());


        return convertView;
    }



}
