package com.example.expensetracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static classObject.Constants.FIRST_COLUMN;
import static classObject.Constants.SECOND_COLUMN;

import classObject.*;

/**
 * Created by yueweiyang on 3/3/17.
 */

public class ListViewAdapter extends BaseAdapter{
    //public ArrayList<HashMap<String, String>> list;
    public ArrayList<Spending> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;


    public ListViewAdapter(Activity activity, ArrayList<Spending> list){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.column_row, null);

            txtFirst=(TextView) convertView.findViewById(R.id.catgoryview);
            txtSecond=(TextView) convertView.findViewById(R.id.amountview);

        }

        Spending temp = list.get(position);
        txtFirst.setText(temp.getCategory());
        txtSecond.setText(Double.toString(temp.getAmount()));


        return convertView;
    }
}
