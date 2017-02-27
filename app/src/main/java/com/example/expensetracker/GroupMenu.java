package com.example.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class GroupMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_menu);
    }

    public void mainMenu(View view) {
        Intent backtoMain = new Intent(GroupMenu.this,MainMenu.class);
        startActivity(backtoMain);
        overridePendingTransition(R.animator.zoom_enter,R.animator.zoom_exit);
    }

    public void addGroupEvent(View view){
        Intent addGroupWindow = new Intent(GroupMenu.this,AddGroup.class);
        startActivity(addGroupWindow);
    }
}
