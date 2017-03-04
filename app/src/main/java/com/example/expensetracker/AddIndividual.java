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
    Spending spendingInfo;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_individual);
        Intent inten1 = getIntent();
        spendingInfo = inten1.getParcelableExtra("detail");
        ET_CAT = (EditText)findViewById(R.id.category);
        ET_AMT = (EditText)findViewById(R.id.amount);
        ET_DES = (EditText)findViewById(R.id.description_individual);
        if (spendingInfo!=null){
            ET_CAT.setText(spendingInfo.getCategory());
            ET_AMT.setText(Double.toString(spendingInfo.getAmount() ) );
            ET_DES.setText(spendingInfo.getDescription());
            position = (int)inten1.getSerializableExtra("position");
        }
        else {
            position = -1;
        }
    }

    public void cancelCreate(View view){
        finish();
    }

    public void saveCreate(View view){
        name = "Yuewei Yang";
        category = ET_CAT.getText().toString();
        description = ET_DES.getText().toString();
        amount = ET_AMT.getText().toString();
        double amountInt = Double.parseDouble(amount);

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
        intent.putExtra("position",position);
        setResult(RESULT_OK,intent);
        finish();
    }
}
