package com.example.expensetracker;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

import classObject.Spending;

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

public class AddEventSpending extends AppCompatActivity {

    String id,name,category,description,amount,method;
    EditText ET_AMT,ET_DES;
    ArrayAdapter<String> adapter_mem,adapter_cat;
    ArrayList<String> members;
    int spendposition,eid;
    Spending spendingInfo;
    Spinner spinner_mem,spinner_cat;

    String s_date;
    Date setDate;
    Button date;
    TextView datetext;
    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_spending);

        ET_AMT = (EditText)findViewById(R.id.amount_event);
        ET_DES = (EditText)findViewById(R.id.Description_event);
        date = (Button)findViewById(R.id.event_date);
        datetext = (TextView) findViewById(R.id.event_date_text);

        Intent inten1 = getIntent();
        members = inten1.getStringArrayListExtra("members");
        spendingInfo = inten1.getParcelableExtra("detail");
        if (spendingInfo!=null){

            ET_AMT.setText(Double.toString(spendingInfo.getAmount() ) );
            ET_DES.setText(spendingInfo.getDescription());

            spendposition = (int)inten1.getSerializableExtra("spendposition");
            id = Integer.toString(spendingInfo.getId());
            eid = spendingInfo.getEventId();

            s_date = spendingInfo.get_s_date();
            datetext.setText(s_date);

            int owner = members.indexOf(spendingInfo.getName());
            Collections.swap(members,owner,0);

            int cat = CategoryList.indexOf(spendingInfo.getCategory());
            Collections.swap(CategoryList,cat,0);
        }
        else {
            spendposition = -1;
            id = Integer.toString(inten1.getIntExtra("id",0)+1);
            eid = inten1.getIntExtra("eid",0);

            Calendar calendar = Calendar.getInstance();
            setDate = calendar.getTime();
            s_date = ft.format(setDate);
            datetext.setText(s_date);

        }

        spinner_mem = (Spinner)findViewById(R.id.Member_name);
        adapter_mem = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,members);
        adapter_mem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mem.setAdapter(adapter_mem);

        spinner_cat = (Spinner)findViewById(R.id.cat_spinner);
        adapter_cat = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,CategoryList);
        adapter_cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cat.setAdapter(adapter_cat);

        date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog(0);
            }
        });


    }


    public void backToEvent(View view){
        finish();
    }

    public void saveEventSpend(View view){

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


        name = spinner_mem.getSelectedItem().toString();
        category = spinner_cat.getSelectedItem().toString();

        double Amount = Double.parseDouble(amount);

        spendingInfo = new Spending();
        spendingInfo.setId(Integer.parseInt(id));
        spendingInfo.setAmount(Amount);
        spendingInfo.setDescription(description);
        spendingInfo.setCategory(category);
        spendingInfo.setName(name);
        spendingInfo.setEventId(eid);
        spendingInfo.set_s_date(s_date);

        if (spendposition==-1) {
            method = "addEventSpend";
        }
        else method = "updateEventSpend";

        EventSpendingBackgroundTask eventSpendingBackgroundTask = new EventSpendingBackgroundTask(this);
        eventSpendingBackgroundTask.execute(method,id,amount,name,category,description,Integer.toString(eid),s_date);

        Intent intent = new Intent();
        intent.putExtra("spendposition",spendposition);
        intent.putExtra("spending",spendingInfo);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void deleteSpending(View view){
        if (spendposition!=-1){
            String method = "deleteEventSpend";
            EventSpendingBackgroundTask eventSpendingBackgroundTask = new EventSpendingBackgroundTask(this);
            eventSpendingBackgroundTask.execute(method,id,Integer.toString(eid));
            Intent intent = new Intent();
            intent.putExtra("spendposition",spendposition);
            setResult(2,intent);
            finish();
        }
    }


    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(AddEventSpending.this, date_listener, year,
                month, day);

    }


    DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            setDate = new GregorianCalendar(year, month, day).getTime();
            s_date = ft.format(setDate);
            datetext.setText(s_date);
        }
    };


}
