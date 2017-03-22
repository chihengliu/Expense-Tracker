package com.example.expensetracker;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import classObject.Global;
import classObject.Spending;

class Downloader extends AsyncTask<String, Void, String> {

    private Context c;
    private String address;
    private ArrayList<Spending> spendings;
    private ProgressDialog pd;
    private Activity activity;

    Downloader(Context c, String address, Activity activity){
        this.c = c;
        this.address = address;
        this.activity = activity;

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


        return downloadData();

    }

    @Override
    protected void onPostExecute(String s){

        pd.dismiss();
        if(s != null){
            Parser p = new Parser(c,s,activity);
            p.execute();

        }else{
            Toast.makeText(c,"Unable to download data",Toast.LENGTH_SHORT).show();
        }

    }

    private String downloadData(){
        //connect and get stream
        InputStream is = null;
        String line;
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    sb.append(line).append("n");
                }

            return sb.toString();
        } catch(IOException e){
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
