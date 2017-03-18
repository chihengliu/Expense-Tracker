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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button)findViewById(R.id.Reg_reg);
        cancel = (Button)findViewById(R.id.Reg_cancel);
        userName = (EditText)findViewById(R.id.Reg_UserName);
        passwd = (EditText)findViewById(R.id.Reg_Passwd);

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //http request
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });


    }
}
