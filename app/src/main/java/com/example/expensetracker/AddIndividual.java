package com.example.expensetracker;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
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
import android.widget.Spinner;
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
import java.util.Collections;
import java.util.Date;
import java.sql.Time;
import java.util.GregorianCalendar;

import static classObject.Constants.CategoryList;


public class AddIndividual extends AppCompatActivity {
    EditText ET_CAT,ET_AMT,ET_DES;
    String name,category,description,amount,id;
    Spending spendingInfo;
    int position;
    String method;
    int thisyear;
    int thismonth;
    int thisday;

    Spinner spinner_cat;
    ArrayAdapter adapter_cat;


    Button date;
    TextView datetext;

    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");


    /*======================Time Picker View==============================*/


    protected Dialog onCreateDialog(int id) {

        // Get the calander
        Calendar c = Calendar.getInstance();

        // From calander get the year, month, day, hour, minute
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        //int hour = c.get(Calendar.HOUR_OF_DAY);
        //int minute = c.get(Calendar.MINUTE);

//        System.out.println(year);
//        System.out.println(month);
//        System.out.println(day);
//        System.out.println(hour);


        // Open the datepicker dialog
        return new DatePickerDialog(AddIndividual.this, date_listener, year,
                month, day);

    }



    // Date picker dialog
    DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // store the data in one string and set it to text
            Date setTime = new GregorianCalendar(year, month, day).getTime();
//            set_date.setText(date1);
            datetext.setText(ft.format(setTime));
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_individual);
        Intent inten1 = getIntent();
        name = inten1.getStringExtra("name");
        spendingInfo = inten1.getParcelableExtra("detail");
        //ET_CAT = (EditText)findViewById(R.id.category);
        ET_AMT = (EditText)findViewById(R.id.amount);
        ET_DES = (EditText)findViewById(R.id.description_individual);
        if (spendingInfo!=null){
            //ET_CAT.setText(spendingInfo.getCategory());
            ET_AMT.setText(Double.toString(spendingInfo.getAmount() ) );
            ET_DES.setText(spendingInfo.getDescription());
            position = (int)inten1.getSerializableExtra("position");
            id = Integer.toString(spendingInfo.getId());

            int cat = CategoryList.indexOf(spendingInfo.getCategory());
            Collections.swap(CategoryList,cat,0);
        }
        else {
            position = -1;
            id = Integer.toString(inten1.getIntExtra("id",0)+1);
        }


        spinner_cat = (Spinner)findViewById(R.id.ind_spinner);
        adapter_cat = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,CategoryList);
        adapter_cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cat.setAdapter(adapter_cat);

        //date picker
        date = (Button)findViewById(R.id.selectdate);
        datetext = (TextView) findViewById(R.id.dateText);
        //timetext = (TextView) findViewById(R.id.timeText);

        Date curdate = new Date();
        //Time curtime = new Time();

        //System.out.println(curdate);
        //System.out.println(curtime);

        //Date curtime = Calendar.getInstance().getTime();
        //System.out.println(curtime);

        Calendar calendar = Calendar.getInstance();
        thisyear = calendar.get(Calendar.YEAR);
        thismonth = calendar.get(Calendar.MONTH);
        thisday = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println(thisyear);
        System.out.println(thismonth);
        System.out.println(thisday);

        Date presentTime = calendar.getTime();
        String date_s = ft.format(presentTime);

        datetext.setText(date_s);


        date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Show Date dialog
                showDialog(0);

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


        /*
        if (TextUtils.isEmpty(ET_CAT.getText())){
            category = "Unknown";
        }
        else{
            category = ET_CAT.getText().toString();
        }*/


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
        category = spinner_cat.getSelectedItem().toString();

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
