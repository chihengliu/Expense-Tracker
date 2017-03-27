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

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }
    BackgroundTask(Context ctx,Activity act) {
        this.ctx = ctx;
        this.activity = act;
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
                }

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
                else {
                    username = params[1];
                    password = params[2];
                    data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
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
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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

        else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }

}
