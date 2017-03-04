package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import classObject.*;


public class MainMenu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Global global = new Global();
    }

    public void individualMenu(View view){
        Intent startIndividualMenu = new Intent(MainMenu.this, IndividualMenuActivity.class);
        startActivity(startIndividualMenu);
        overridePendingTransition(R.animator.zoom_enter,R.animator.zoom_exit);
    }

    public void groupMenu(View view){
        Intent startGroupMenu = new Intent(MainMenu.this, GroupMenu.class);
        startActivity(startGroupMenu);
        overridePendingTransition(R.animator.zoom_enter,R.animator.zoom_exit);
    }


}
