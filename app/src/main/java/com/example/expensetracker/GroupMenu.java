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
        String method = "updateMembers";
        Collections.reverse(events);
        BackgroundTask backgroundTask = new BackgroundTask(this,GroupMenu.this,events);
        backgroundTask.execute(method);

        //Intent addGroupWindow = new Intent(GroupMenu.this,AddGroup.class);
        //startActivityForResult(addGroupWindow,REQEST_CODE_ADD_IND);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQEST_CODE_ADD_IND){
            if (resultCode==RESULT_OK) {
                Event info = data.getParcelableExtra("eventInfo");
                int position = (int) data.getSerializableExtra("position");

                if (position == -1) {
                    events.add(0, info);
                } else {
                    events.set(position, info);
                }


            }
            else if (resultCode == 2)
            {
                int position = (int) data.getSerializableExtra("position");
                events.remove(position);

            }

            adapter = new ListViewAdapterEvent(this, events);
            //adapter.updateList(list);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
                {
                    int pos=position+1;
                    if (pos!=events.size()) {
                        Intent intent = new Intent(GroupMenu.this, EventPage.class);
                        intent.putExtra("detail", events.get(position));
                        intent.putExtra("position", position);
                        startActivityForResult(intent, REQEST_CODE_ADD_IND);
                    }
                }

            });


        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
