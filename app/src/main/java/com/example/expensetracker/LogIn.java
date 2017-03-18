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
        loginButton = (Button)findViewById(R.id.LogInButton);
        registerButton = (Button)findViewById(R.id.Login_Reg);
        userName = (EditText)findViewById(R.id.Log_UserName);
        passwd = (EditText)findViewById(R.id.Log_Passwd);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToMain = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(goToMain);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToReg = new Intent(getApplicationContext(), Register.class);
                startActivity(goToReg);
            }
        });

    }






}
