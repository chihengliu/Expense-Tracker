package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

import static classObject.Constants.FIRST_COLUMN;
import static classObject.Constants.SECOND_COLUMN;

public class individual_detail extends AppCompatActivity {
    private HashMap<String, String> detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_detail);
        Intent intent = getIntent();
        detail = (HashMap<String, String>)intent.getSerializableExtra("detail");
        TextView cat = (TextView) findViewById(R.id.category_detal);
        cat.setText(detail.get(FIRST_COLUMN));
        TextView am = (TextView) findViewById(R.id.amount_detail);
        am.setText(detail.get(SECOND_COLUMN));
        TextView des = (TextView) findViewById(R.id.description_detail);
        des.setText(detail.get("Third"));

    }


}
