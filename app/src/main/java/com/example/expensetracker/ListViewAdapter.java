package com.example.expensetracker;

import android.app.Activity;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import classObject.*;


class ListViewAdapter extends BaseAdapter{
    private ArrayList<Spending> list;
    private Activity activity;
    private TextView txtFirst;
    private TextView txtSecond;

    ListViewAdapter(Activity activity, ArrayList<Spending> list){
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

            convertView=inflater.inflate(R.layout.column_row, parent,false);

            txtFirst=(TextView) convertView.findViewById(R.id.catgoryview);
            txtSecond=(TextView) convertView.findViewById(R.id.amountview);

        }

        Spending temp = list.get(position);
        txtFirst.setText(temp.getCategory());
        if (temp.getAmount()!=-1) {

            txtSecond.setText(String.format(Locale.getDefault(),"%.2f",temp.getAmount()));
        }
        else {
            txtSecond.setText(null);
        }


        return convertView;
    }



}
