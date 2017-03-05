package com.example.expensetracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import classObject.Spending;

public class Downloader extends AsyncTask<String, Void, String> {

    Context c;
    String address;
    ArrayList<Spending> spendings;
    ProgressDialog pd;

    public Downloader(Context c, String address){
        this.c = c;
        this.address = address;

    }
    @Override
    protected void onPreExecute(){
        pd=new ProgressDialog(c);
        pd.setTitle("Fetch Data");
        pd.setMessage("Fetching Data...Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        android.os.Debug.waitForDebugger();

        return downloadData();

    }

    @Override
    protected void onPostExecute(String s){

        pd.dismiss();
        if(s != null){
            try{
                //Add data to JSON Array
                JSONArray ja = new JSONArray(s);
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

            }catch (JSONException e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(c,"Unable to download data",Toast.LENGTH_SHORT).show();
        }

    }

    private String downloadData(){
        //connect and get stream
        InputStream is = null;
        String line = null;
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer sb = new StringBuffer();
            if (br != null) {
                while ((line = br.readLine()) != null) {
                    sb.append(line + "n");
                }
            } else {
                return null;
            }
            return sb.toString();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(is != null){
                try{
                    is.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public ArrayList<Spending> getSpendings(){
        return this.spendings;
    }
}