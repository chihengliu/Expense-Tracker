package com.example.expensetracker;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import classObject.Spending;

/**
 * Created by yueweiyang on 3/31/17.
 */

public class EventSpendingBackgroundTask extends AsyncTask<String,Void,ArrayList<Spending>>{

    AlertDialog alertDialog;
    Context ctx;
    String data;
    URL url;
    ArrayList<Spending> spendings;

    EventSpendingBackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Add Information....");
    }

    @Override
    protected ArrayList<Spending> doInBackground(String... params) {
        String downEventSpend_url = "http://152.3.52.123/downEventSpend.php";
        String addEventSpen_url = "http://152.3.52.123/addEventSpend.php";
        String updateEventSpen_erl = "http://152.3.52.123/updateEventSpend.php";
        String method = params[0];
        String line;



        try {

            switch (method) {
                case "downEventSpend":
                    url = new URL(downEventSpend_url);
                    break;
                case "addEventSpend":
                    url = new URL(addEventSpen_url);
                    break;
                case "updateEventSpend":
                    url = new URL(updateEventSpen_erl);
            }

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        if (method.equals("addEventSpend") || method.equals("updateEventSpend")){
            String id = params[1];
            String amount = params[2];
            String name = params[3];
            String category = params[4];
            String description = params[5];
            String eid = params[6];
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("amount", amount);
            jsonObject.put("name", name);
            jsonObject.put("category", category);
            jsonObject.put("description", description);
            jsonObject.put("eid",eid);
            data = jsonObject.toString();

        }

        if (method.equals("downEventSpend")){
            String id = params[1];
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("eid", id);
            data = jsonObject.toString();
        }

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();


            InputStream IS = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS));
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            String result = sb.toString();



            IS.close();
            //httpURLConnection.connect();
            httpURLConnection.disconnect();

            if (method.equals("downEventSpend")){
                result = result.trim();
                spendings = new ArrayList<>();
                Spending spending = new Spending();
                spending.setCategory("");
                spending.setDescription("");
                spending.setName("");
                spending.setAmount(-1);
                spendings.add(spending);
                if (result.equals("null")){
                    return spendings;
                }
                else {
                    JSONArray ja = new JSONArray(result);
                    JSONObject jo;

                    for (int i=0;i<ja.length();i++){
                        jo = ja.getJSONObject(i);
                        String Category = jo.getString("Category");
                        String Name = jo.getString("Name");
                        String Description = jo.getString("Description");
                        double Amount = jo.getDouble("Amount");
                        int eid = jo.getInt("eid");
                        int id = jo.getInt("sid");
                        spending = new Spending();
                        spending.setId(id);
                        spending.setCategory(Category);
                        spending.setName(Name);
                        spending.setAmount(Amount);
                        spending.setDescription(Description);
                        spending.setEventId(eid);
                        spendings.add(spending);

                    }
                    return spendings;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(ArrayList<Spending> result) {

    }

}
