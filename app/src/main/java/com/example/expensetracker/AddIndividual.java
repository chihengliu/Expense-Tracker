package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddIndividual extends AppCompatActivity {
    EditText ET_CAT,ET_AMT,ET_DES;
    String name,category,description,amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_individual);
        ET_CAT = (EditText)findViewById(R.id.category);
        ET_AMT = (EditText)findViewById(R.id.amount);
        ET_DES = (EditText)findViewById(R.id.description_individual);
    }

    public void cancelCreate(View view){
        Intent backtoIndividual = new Intent(AddIndividual.this,IndividualMenuActivity.class);
        startActivity(backtoIndividual);
    }

    public void saveCreate(View view){
        name = "Yuewei Yang";
        category = ET_CAT.getText().toString();
        description = ET_DES.getText().toString();
        amount = ET_AMT.getText().toString();
        ArrayList<String> spendingInfo = new ArrayList<String>();
        spendingInfo.add(name);
        spendingInfo.add(description);
        spendingInfo.add(amount);
        spendingInfo.add(category);

        String method = "addSpend";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,name,category,description,amount);
        Intent gotoIndividual = new Intent(AddIndividual.this,IndividualMenuActivity.class);
        gotoIndividual.putStringArrayListExtra("spinfo",spendingInfo);
        startActivity(gotoIndividual);
    }
}
