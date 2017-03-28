package com.example.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import classObject.Event;

public class GroupMenu extends AppCompatActivity {
    private ArrayList<Event> events;
    ListViewAdapterEvent adapter;
    ListView listView;
    private static final int REQEST_CODE_ADD_IND = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_menu);

        Intent intent2 = getIntent();
        events = intent2.getParcelableArrayListExtra("eventlist");
        Collections.reverse(events);
        adapter=new ListViewAdapterEvent(this, events);
        listView = (ListView) findViewById(R.id.event_list);
        listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
                if (pos!=events.size()) {
                    Toast.makeText(GroupMenu.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GroupMenu.this, EventPage.class);
                    intent.putExtra("detail", events.get(position));
                    intent.putExtra("position", position);
                    intent.putExtra("name", events.get(position).getName());
                    startActivityForResult(intent, REQEST_CODE_ADD_IND);
                }
            }

        });




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
