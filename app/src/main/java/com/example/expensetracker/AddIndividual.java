package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Collections;

import classObject.*;

import static classObject.Constants.CategoryList;

public class AddIndividual extends AppCompatActivity {
    EditText ET_AMT,ET_DES;
    String name,category,description,amount,id;
    Spending spendingInfo;
    int position;
    String method;
    Spinner spinner_cat;
    ArrayAdapter adapter_cat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_individual);
        Intent inten1 = getIntent();
        name = inten1.getStringExtra("name");
        spendingInfo = inten1.getParcelableExtra("detail");
        ET_AMT = (EditText)findViewById(R.id.amount);
        ET_DES = (EditText)findViewById(R.id.description_individual);
        if (spendingInfo!=null){
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
