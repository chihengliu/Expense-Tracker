package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import classObject.Spending;

public class AddEventSpending extends AppCompatActivity {

    EditText ET_CAT,ET_AMT,ET_DES;
    String id,name,category,description,amount,method;
    int eid;
    Spending spendingInfo;
    int spendposition;
    ArrayList<String> members;
    Spinner spinner;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_spending);
        Intent inten1 = getIntent();
        members = inten1.getStringArrayListExtra("members");
        spendingInfo = inten1.getParcelableExtra("detail");
        ET_CAT = (EditText)findViewById(R.id.Category_event);
        ET_AMT = (EditText)findViewById(R.id.amount_event);
        ET_DES = (EditText)findViewById(R.id.Description_event);
        if (spendingInfo!=null){
            ET_CAT.setText(spendingInfo.getCategory());
            ET_AMT.setText(Double.toString(spendingInfo.getAmount() ) );
            ET_DES.setText(spendingInfo.getDescription());
            spendposition = (int)inten1.getSerializableExtra("spendposition");
            id = Integer.toString(spendingInfo.getId());
            eid = spendingInfo.getEventId();
            int owner = members.indexOf(spendingInfo.getName());
            Collections.swap(members,owner,0);
        }
        else {
            spendposition = -1;
            id = Integer.toString(inten1.getIntExtra("id",0)+1);
            eid = inten1.getIntExtra("eid",0);

        }

        spinner = (Spinner)findViewById(R.id.Member_name);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,members);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

    public void backToEvent(View view){

        finish();
    }

    public void saveEventSpend(View view){
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

        name = spinner.getSelectedItem().toString();

        double Amount = Double.parseDouble(amount);

        spendingInfo = new Spending();
        spendingInfo.setId(Integer.parseInt(id));
        spendingInfo.setAmount(Amount);
        spendingInfo.setDescription(description);
        spendingInfo.setCategory(category);
        spendingInfo.setName(name);
        spendingInfo.setEventId(eid);

        if (spendposition==-1) {
            method = "addEventSpend";
        }
        else method = "updateEventSpend";

        EventSpendingBackgroundTask eventSpendingBackgroundTask = new EventSpendingBackgroundTask(this);
        eventSpendingBackgroundTask.execute(method,id,amount,name,category,description,Integer.toString(eid));

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

}
