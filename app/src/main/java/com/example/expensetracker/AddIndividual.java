package com.example.expensetracker;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import classObject.*;

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
        finish();
    }

    public void saveCreate(View view){
        name = "Yuewei Yang";
        category = ET_CAT.getText().toString();
        description = ET_DES.getText().toString();
        amount = ET_AMT.getText().toString();
        int amountInt = Integer.parseInt(amount);

        //ArrayList<String> spendingInfo = new ArrayList<String>();
        Spending spendingInfo = new Spending();
        //spendingInfo.(name);
        spendingInfo.setDescription(description);
        spendingInfo.setAmount(amountInt);
        spendingInfo.setCategory(category);

        //String method = "addSpend";
        //BackgroundTask backgroundTask = new BackgroundTask(this);
        //backgroundTask.execute(method,name,category,description,amount);
        Intent intent = new Intent();
        intent.putExtra("spinfo",spendingInfo);
        setResult(RESULT_OK,intent);
        finish();
    }
}
