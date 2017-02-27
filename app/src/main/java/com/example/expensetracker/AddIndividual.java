package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddIndividual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_individual);
    }

    public void cancelCreate(View view){
        Intent backtoIndividual = new Intent(AddIndividual.this,IndividualMenuActivity.class);
        startActivity(backtoIndividual);
    }
}
