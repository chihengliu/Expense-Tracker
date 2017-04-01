package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import classObject.*;


public class MainMenu extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

    }

    public void individualMenu(View view){

        String url = "http://152.3.52.123/updateList.php";
        Downloader downloader = new Downloader(this,url,MainMenu.this);
        downloader.execute(name);
    }

    public void groupMenu(View view){

        String method = "updateEventList";
        BackgroundTask backgroundTask = new BackgroundTask(this,MainMenu.this);
        backgroundTask.execute(method,name);
        /*Intent startGroupMenu = new Intent(MainMenu.this, GroupMenu.class);
        startActivity(startGroupMenu);
        overridePendingTransition(R.animator.zoom_enter,R.animator.zoom_exit);*/
    }

    public void logout(View view){
        Intent logout = new Intent(MainMenu.this, LogIn.class);
        startActivity(logout);
    }


}
