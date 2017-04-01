package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    Button  register, cancel;
    EditText userName, passwd;
    String method,UserName,PassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button) findViewById(R.id.Reg_reg);
        cancel = (Button) findViewById(R.id.Reg_cancel);
        userName = (EditText) findViewById(R.id.Reg_UserName);
        passwd = (EditText) findViewById(R.id.Reg_Passwd);
    }


    public void register(View v) {
        method = "register";
        UserName = userName.getText().toString();
        PassWord = passwd.getText().toString();
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,UserName,PassWord);
        finish();

    }


    public void cancel(View v) {
        finish();
    }




}
