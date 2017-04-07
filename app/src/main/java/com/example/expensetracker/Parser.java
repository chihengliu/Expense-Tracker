package com.example.expensetracker;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import classObject.Spending;

class Parser extends AsyncTask<Void,Integer,Integer>{
    private Context c;
    private String data;
    private ArrayList<Spending> spendings;
    private ProgressDialog pd;
    private Activity activity;
    private String name;

    Parser(Context c,String data,Activity activity,String name){
        this.c = c;
        this.data = data;
        this.activity = activity;
        this.name = name;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parsing ....Please wait");
        pd.show();
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer){
        super.onPostExecute(integer);
        if (integer == 1){

            Intent intent = new Intent(this.activity,IndividualMenuActivity.class);
            intent.putExtra("list",spendings);
            activity.startActivity(intent);

        }else{
            Toast.makeText(c,"Unable to Parse",Toast.LENGTH_SHORT).show();
        }
        pd.dismiss();
    }

    //Parse received data
    private int parse(){
        try{
            //Add data to JSON Array

            spendings = new ArrayList<>();
            Spending spending = new Spending();
            spending.setName(name);
            spending.setCategory("");
            spending.setAmount(-1);
            spending.setDescription("");
            spending.setId(0);
            spending.set_s_date("1980.01.01");
            spendings.add(spending);

            if (data.toString().trim().equals("nulln")){


                return 1;
            }
            JSONArray ja = new JSONArray(data);
            //Create JSON object to hold a single item
            JSONObject jo;

            //Loop through array
            for(int i=0;i<ja.length();i++){
                jo = ja.getJSONObject(i);

                //Retrieve fields
                String category = jo.getString("Category");
                double amount = jo.getDouble("Amount");
                String description = jo.getString("Description");
                int id = jo.getInt("sid");
                String s_date = jo.getString("Date");

                //Add them to list
                spending = new Spending();
                spending.setName(name);
                spending.setCategory(category);
                spending.setAmount(amount);
                spending.setDescription(description);
                spending.setId(id);
                spending.set_s_date(s_date);
                spendings.add(spending);

            }
            return 1;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return 0;
    }

}
