package com.example.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;


public class LogIn extends AppCompatActivity {
    Button loginButton;
    Button registerButton;

    EditText userName, passwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loginButton = (Button) findViewById(R.id.LogInButton);

        userName = (EditText) findViewById(R.id.Log_UserName);
        passwd = (EditText) findViewById(R.id.Log_Passwd);
    }

    public void login(View view) {
        String method = "login";
        String username = userName.getText().toString();
        String password = passwd.getText().toString();
        BackgroundTask backgroundTask = new BackgroundTask(this,LogIn.this);
        backgroundTask.execute(method,username,password);
    }



    public void register(View v) {
        Intent goToReg = new Intent(LogIn.this, Register.class);
        startActivity(goToReg);
    }









}
