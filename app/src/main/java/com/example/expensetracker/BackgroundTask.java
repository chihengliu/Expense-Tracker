package com.example.expensetracker;


        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.widget.Toast;
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

/**
 * Created by yueweiyang on 3/1/17.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    AlertDialog alertDialog;
    Context ctx;
    String data;
    URL url;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
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
        String method = params[0];
        String line;



            try {


                switch (method){
                    case "addSpend":
                        url = new URL(addS_url);
                        break;
                    case "update":
                        url = new URL(update_url);
                        break;
                    case "register":
                        url = new URL(register_url);
                        break;
                    case "login":
                        url = new URL(login_url);
                        break;
                }

                if (method.equals("addSpend") || method.equals("update")) {
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
                else {
                    String username = params[1];
                    String password = params[2];
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

                String result = sb.toString().trim();



                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                if (method.equals("addSpend")) {
                    return "Add Spending Success...";
                }
                if (method.equals("update")) {
                    return "Edit Spending Success...";
                }
                if (method.equals("register")) {
                    if (result.equals("success")) {
                        return "Register Success...";
                    }
                    else {
                        return "Register Fail... Change User Name";
                    }
                }
                if (method.equals("login")) {
                    if (result.equals("success")) {

                        return "Login Success...";
                    } else {
                        return "Login Fail... \n Incorrect Password";
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
        else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }

}
