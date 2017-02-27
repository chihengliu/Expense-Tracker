package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
    }

    public void cancelCreate(View view) {
        Intent backtoGroupMenu = new Intent(AddGroup.this, GroupMenu.class);
        startActivity(backtoGroupMenu);
        overridePendingTransition(R.animator.zoom_enter, R.animator.zoom_exit);
    }

    public void saveGroup(View view) {
        Intent saveGroupInfo = new Intent(AddGroup.this,EventPage.class);
        startActivity(saveGroupInfo);
    }
}
