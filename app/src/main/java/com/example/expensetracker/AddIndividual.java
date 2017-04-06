package com.example.expensetracker;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import classObject.*;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.net.Credentials;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;


public class AddIndividual extends AppCompatActivity {
    EditText ET_CAT,ET_AMT,ET_DES;
    String name,category,description,amount,id;
    Spending spendingInfo;
    int position;
    String method;


     Button date, time;
    // TextView set_date, set_time;
    private static final int Date_id = 0;
    private static final int Time_id = 1;


    /*======================Time Picker View==============================*/


    protected Dialog onCreateDialog(int id) {

        // Get the calander
        Calendar c = Calendar.getInstance();

        // From calander get the year, month, day, hour, minute
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        switch (id) {
            case Date_id:

                // Open the datepicker dialog
                return new DatePickerDialog(AddIndividual.this, date_listener, year,
                        month, day);
            case Time_id:

                // Open the timepicker dialog
                return new TimePickerDialog(AddIndividual.this, time_listener, hour,
                        minute, false);

        }
        return null;
    }



    // Date picker dialog
    DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // store the data in one string and set it to text
            String date1 = String.valueOf(month + 1) + "/" + String.valueOf(day)
                    + "/" + String.valueOf(year);
//            set_date.setText(date1);
//            theyear = year;
//            themonth = month;
//            theday = day;
        }
    };
    TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            // store the data in one string and set it to text
            String time1 = String.valueOf(hour) + ":" + String.valueOf(minute);
//            set_time.setText(time1);
//            thehour = hour;
//            themin = minute;
        }
    };

    private boolean checkDigit(String str){
        for(int i = 0; i < str.length(); i++){
            if ((str.charAt(i) > '9') || (str.charAt(i) < '0')) {
                return false;
            }
        }
        return true;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_individual);
        Intent inten1 = getIntent();
        name = inten1.getStringExtra("name");
        spendingInfo = inten1.getParcelableExtra("detail");
        ET_CAT = (EditText)findViewById(R.id.category);
        ET_AMT = (EditText)findViewById(R.id.amount);
        ET_DES = (EditText)findViewById(R.id.description_individual);
        if (spendingInfo!=null){
            ET_CAT.setText(spendingInfo.getCategory());
            ET_AMT.setText(Double.toString(spendingInfo.getAmount() ) );
            ET_DES.setText(spendingInfo.getDescription());
            position = (int)inten1.getSerializableExtra("position");
            id = Integer.toString(spendingInfo.getId());

        }
        else {
            position = -1;
            id = Integer.toString(inten1.getIntExtra("id",0)+1);
        }

        //date picker
        date = (Button)findViewById(R.id.selectdate);
        time = (Button)findViewById(R.id.selecttime);
        //set_date = (TextView) findViewById(R.id.set_date);
        //set_time = (TextView) findViewById(R.id.set_time);
        date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Show Date dialog
                showDialog(Date_id);

            }
        });
        time.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Show time dialog
                showDialog(Time_id);
            }
        });
    }

    public void cancelCreate(View view){
        finish();
    }

    public void deleteCreate(View view){

        if(position != -1){
            Intent intent = new Intent();
            intent.putExtra("position",position);
            setResult(2, intent);
            BackgroundTask backgroundTask = new BackgroundTask(this);
            String method = "deleteSpend";
            backgroundTask.execute(method,name,id);
            finish();
        }

    }

    public void saveCreate(View view){
        //name = spendingInfo.getName();


        if (TextUtils.isEmpty(ET_CAT.getText())){
            category = "Unknown";
        }
        else{
            category = ET_CAT.getText().toString();
        }


        if (TextUtils.isEmpty(ET_DES.getText())){
            description = "Unknown";
        }
        else{
            description = ET_DES.getText().toString();
        }

        if (TextUtils.isEmpty(ET_AMT.getText())){
            amount = "0";
        }
        else{
            amount = ET_AMT.getText().toString();
        }


        double amountInt = Double.parseDouble(amount);



        //ArrayList<String> spendingInfo = new ArrayList<String>();
        spendingInfo = new Spending();
        spendingInfo.setName(name);
        spendingInfo.setDescription(description);
        spendingInfo.setAmount(amountInt);
        spendingInfo.setCategory(category);
        spendingInfo.setId(Integer.parseInt(id));

        if (position==-1) {
            method = "addSpend";
        }
        else method = "updateSpend";

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,name,category,description,amount,id);

        Intent intent = new Intent();
        intent.putExtra("spinfo",spendingInfo);
        intent.putExtra("position",position);
        setResult(RESULT_OK,intent);
        finish();

    }
}
