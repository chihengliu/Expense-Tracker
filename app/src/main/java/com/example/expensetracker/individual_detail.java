package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import classObject.*;

public class individual_detail extends AppCompatActivity {
    private HashMap<String, String> detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_detail);
        Intent intent = getIntent();
        Spending detail = intent.getParcelableExtra("detail");
        TextView cat = (TextView) findViewById(R.id.category_detal);
        cat.setText(detail.getCategory());
        TextView am = (TextView) findViewById(R.id.amount_detail);
        am.setText(Double.toString(detail.getAmount()));
        TextView des = (TextView) findViewById(R.id.description_detail);
        des.setText(detail.getDescription());

    }


}
