package com.example.expensetracker;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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
import java.net.URLEncoder;
import java.util.ArrayList;

import classObject.Event;
import classObject.Spending;

/**
 * Created by yueweiyang on 3/1/17.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    AlertDialog alertDialog;
    Context ctx;
    String data;
    URL url;
    Activity activity;
    String password;
    String username;
    ArrayList<String> array;

    ArrayList<Event> events;
    ArrayList<String> members;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }
    BackgroundTask(Context ctx,Activity act, ArrayList<Event> events){
        this.ctx = ctx;
        this.activity = act;
        this.events = events;
    }

    BackgroundTask(Context ctx,Activity act) {
        this.ctx = ctx;
        this.activity = act;
    }

    BackgroundTask(Context ctx, ArrayList<String> array) {
        this.ctx = ctx;
        this.array = array;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Add Information....");
    }

    @Override
    protected String doInBackground(String... params) {
        String addS_url = "http://152.3.52.123/addSpending.php";
        String update_url = "http://152.3.52.123/updateSpending.php";
        String register_url = "http://152.3.52.123/register.php";
        String login_url = "http://152.3.52.123/login.php";
        String delete_url = "http://152.3.52.123/deleteSpending.php";
        String updateEvent_url = "http://152.3.52.123/updateEventList.php";
        //String updateMember_url = "http://152.3.52.123/updateMembers.php";
        //String addEvent_url = "http://152.3.52.123/addEvent.php";
        //String updateE_url = "http://152.3.52.123/updateEvent.php";
        String addEventAndMember_url = "http://152.3.52.123/addEventAndMember.php";
        String updateEventAndMember_url = "http://152.3.52.123/updateEventAndMember.php";
        String method = params[0];
        String line;



        try {




            switch (method){
                case "addSpend":
                    url = new URL(addS_url);
                    break;
                case "updateSpend":
                    url = new URL(update_url);
                    break;
                case "register":
                    url = new URL(register_url);
                    break;
                case "login":
                    url = new URL(login_url);
                    break;
                case "deleteSpend":
                    url = new URL(delete_url);
                    break;
                case "updateEventList":
                    url = new URL(updateEvent_url);
                    break;
                case "addEventAndMember" :
                    url = new URL(addEventAndMember_url);
                    break;
                case "updateEventAndMember" :
                    url = new URL(updateEventAndMember_url);
                    break;
            }

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (method.equals("addSpend") || method.equals("updateSpend")) {
                String name = params[1];
                String category = params[2];
                String description = params[3];
                String amount = params[4];
                String id = params[5];
                data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(category, "UTF-8") + "&" +
                        URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8") + "&" +
                        URLEncoder.encode("amount", "UTF-8") + "=" + URLEncoder.encode(amount, "UTF-8");

            }
            else if (method.equals("deleteSpend")) {
                String name = params[1];
                String id = params[2];
                data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
            }
            else if (method.equals("updateEventList")){
                username = params[1];
                httpURLConnection.setRequestProperty("Content-Type", "application/json");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", username);
                data = jsonObject.toString();

                //data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            }
            else if (method.equals("addEventAndMember")){
                //String eventId = params[1];
                String name = params[1];
                String description = params[2];

                // here you are setting the 'Content-Type' for the data you are sending which is 'application/json'
                httpURLConnection.setRequestProperty("Content-Type", "application/json");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", name);
                jsonObject.put("description", description);

                //JSONArray memberList = new JSONArray();
                jsonObject.put("members", new JSONArray(array));

                data = jsonObject.toString();

            }
            else if (method.equals("updateEventAndMember")){
                String eventId = params[1];
                int EventID = Integer.valueOf(eventId);
                String name = params[2];
                String description = params[3];

                // here you are setting the 'Content-Type' for the data you are sending which is 'application/json'
                httpURLConnection.setRequestProperty("Content-Type", "application/json");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", EventID);
                jsonObject.put("name", name);
                jsonObject.put("description", description);

                System.out.println(EventID);

                //JSONArray memberList = new JSONArray();
                jsonObject.put("members", new JSONArray(array));

                data = jsonObject.toString();

            }
            else {
                username = params[1];
                password = params[2];
                data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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
            if (method.equals("addSpend")) {
                return "Add Spending Success...";
            }
            if (method.equals("updateSpend")) {
                return "Edit Spending Success...";
            }
            if (method.equals("deleteSpend")) {
                return "Delete Spending Success...";
            }
            if (method.equals("register")) {
                result = result.trim();
                if (result.equals("success")) {
                    return "Register Success...";
                }
                else {
                    return "Register Fail... Change User Name";
                }
            }
            if (method.equals("login")) {
                result = result.trim();
                if (result.equals("null")) {
                    return "Login Fail... Check User Name";
                }
                else {
                    try {
                        //Add data to JSON Array
                        JSONArray ja = new JSONArray(result);
                        //Create JSON object to hold a single item
                        JSONObject jo = ja.getJSONObject(0);
                        String corrPassword = jo.getString("Password");
                        if (password.equals(corrPassword)) {
                            Intent goMain = new Intent(this.activity, MainMenu.class);
                            goMain.putExtra("name", username);
                            activity.startActivity(goMain);
                            return "Login Success...";
                        } else {
                            return "Login Fail... Check Password";
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
            if (method.equals("updateEventList")){
                ArrayList<Event> events = new ArrayList<Event>();
                Event event = new Event();
                event.setName("");
                event.setId(0);
                event.setDescription("");
                events.add(event);

                if (result.equals("nulln")) {

                }
                else {
                    try {
                        //Add data to JSON Array
                        JSONArray ja = new JSONArray(result);
                        JSONObject jo;

                        int totalNum = ja.getJSONObject(ja.length()-1).getInt("totalNum");
                        Event tempEvent = new Event();

                        //Create JSON object to hold a single item
                        for(int i=0; i < ja.length() - totalNum-1; i++) {
                            jo = ja.getJSONObject(i);
                            if (jo.length() > 4) {
                                String Name = jo.getString("Name");
                                String Description = jo.getString("Description");
                                int id = jo.getInt("EID");
                                event = new Event();
                                event.setDescription(Description);
                                event.setId(id);
                                event.setName(Name);
                                events.add(event);
                                tempEvent = event;
                            }
                            else {
                                String memberName = jo.getString("User_Name");
                                tempEvent.addMember(memberName);
                            }
                        }

                        members = new ArrayList<>();
                        for(int i=ja.length() - totalNum;i<ja.length()-1;i++){
                            String membername = ja.getJSONObject(i).getString("Name");
                            members.add(membername);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                Intent goGroup = new Intent(this.activity,GroupMenu.class);
                goGroup.putExtra("eventlist",events);
                goGroup.putExtra("allmembers",members);
                activity.startActivity(goGroup);
                return "Download Event List Success...";
            }
                /*
                if (method.equals("updateMembers")){

                    ArrayList<String> allmembers = new ArrayList<String>();


                        try {
                            //Add data to JSON Array
                            JSONArray ja = new JSONArray(result);
                            JSONObject jo;

                            //Create JSON object to hold a single item
                            for(int i=0;i<ja.length()-1;i++) {
                                jo = ja.getJSONObject(i);
                                String Name = jo.getString("Name");
                                allmembers.add(Name);
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    Intent goMember = new Intent(this.activity,AddGroup.class);
                    goMember.putExtra("memberlist",allmembers);
                    goMember.putExtra("allevents",events);
                    goMember.putExtra("name",username);
                    activity.startActivity(goMember);
                    return "Download Members Success...";
                }*/
            if (method.equals("addEventAndMember")) {
                String eventid = result.trim();
                return eventid;
            }
            if (method.equals("updateEventAndMember")) {
                return "Update Event Success...";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Add Spending Success..."))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Edit Spending Success...")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Register Success...")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Login Success...")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Register Fail... Change User Name")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Login Fail... Check User Name")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Login Fail... Check Password")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Delete Spending Success...")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Download Event List Success...")){

        }
        else if (result.equals("Download Members Success...")){

        }
        else if (result.equals("24")){
            Toast.makeText(ctx, "Add Event Success...", Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Update Event Success...")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }

}
