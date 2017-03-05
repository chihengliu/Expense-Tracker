package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventPage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);


    }

    public void backtoGroupMenu(View veiw){
        finish();
    }

    public void addEventSpending(View view){
        Intent addEventSpending = new Intent(EventPage.this,AddEventSpending.class);
        startActivity(addEventSpending);
    }
}
