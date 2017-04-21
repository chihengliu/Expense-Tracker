package com.example.expensetracker;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class Downloader extends AsyncTask<String, Void, String> {

    private Context c;
    private String address;
    private ProgressDialog pd;
    private Activity activity;
    private String name;

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

        name = params[0];
        return downloadData(name);

    }

    @Override
    protected void onPostExecute(String s){

        pd.dismiss();
        if(s != null){
            Parser p = new Parser(c,s,activity,name);
            p.execute();

        }else{
            Toast.makeText(c,"Unable to download data",Toast.LENGTH_SHORT).show();
        }

    }

    private String downloadData(String name){
        //connect and get stream
        InputStream is = null;
        String line;
        try {

            URL url = new URL(address);
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            OutputStream OS = con.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
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


}
