package com.example.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GroupMenu extends AppCompatActivity {
    String[] eventarray = {"BBQ","KTV","Football","Hiking"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_menu);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(GroupMenu.this,
                R.layout.activity_listview, eventarray);

        ListView listView = (ListView) findViewById(R.id.event_list);
        listView.setAdapter(adapter);
    }

    public void mainMenu(View view) {
        finish();
        overridePendingTransition(R.animator.zoom_enter,R.animator.zoom_exit);
    }

    public void addGroupEvent(View view){
        Intent addGroupWindow = new Intent(GroupMenu.this,AddGroup.class);
        startActivity(addGroupWindow);
    }
}
