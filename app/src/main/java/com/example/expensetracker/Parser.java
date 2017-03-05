package com.example.expensetracker;

import android.app.ProgressDialog;
import android.content.Context;
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

import classObject.Spending;

public class Parser extends AsyncTask<Void,Integer,Integer>{
    Context c;
    ListView lv;
    String data;
    ArrayList<Spending> spendings = new ArrayList<>();
    ProgressDialog pd;

    public Parser(Context c,String data,ListView lv){
        this.c = c;
        this.data = data;
        this.lv = lv;
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
            //Adapter
            ArrayAdapter<Spending> adapter = new ArrayAdapter<>(c,android.R.layout.simple_list_item_1,spendings);
            //Adapt to ListView
            lv.setAdapter(adapter);
            //Listener
            /*
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent,View view,int position, long id){
                    Snackbar.make(view,spendings.get(position),Snackbar.LENGTH_SHORT).show();
                }

            });
            */
        }else{
            Toast.makeText(c,"Unable to Parse",Toast.LENGTH_SHORT).show();
        }
        pd.dismiss();
    }

    //Parse received data
    private int parse(){
        try{
            //Add data to JSON Array
            JSONArray ja = new JSONArray(data);
            //Create JSON object to hold a single item
            JSONObject jo = null;
            spendings.clear();
            //Loop thru array
            for(int i=0;i<ja.length();i++){
                jo = ja.getJSONObject(i);

                //Retrieve fields
                String category = jo.getString("Category");
                double amount = jo.getDouble("Amount");
                String description = jo.getString("Description");

                //Add them to arraylist
                Spending spending = new Spending();
                spending.setCategory(category);
                spending.setAmount(amount);
                spending.setDescription(description);
                spendings.add(spending);

            }
            return 1;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return 0;
    }

}
