package com.example.expensetracker;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import classObject.*;

public class AddIndividual extends AppCompatActivity {
    EditText ET_CAT,ET_AMT,ET_DES;
    String name,category,description,amount,id;
    Spending spendingInfo;
    int position;
    String method;



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
