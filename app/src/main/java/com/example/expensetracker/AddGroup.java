package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;


import java.util.ArrayList;

import classObject.Event;


public class AddGroup extends AppCompatActivity {
    EditText ET_NAME,ET_DES;
    String name, description,method,id;
    ArrayList<String> members;
    Event eventinfo;
    int position;
    ArrayAdapter<String> adapter;
    ListView listView;
    ArrayList<Event> allevents;
    ArrayList<String> allmembers;
    private static final int REQEST_CODE_ADD_IND = 102;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Intent intent = getIntent();
        ET_NAME = (EditText)findViewById(R.id.event);
        ET_DES = (EditText)findViewById(R.id.event_description);
        eventinfo = intent.getParcelableExtra("detail");
        allevents = intent.getParcelableArrayListExtra("allevents");
        allmembers = intent.getStringArrayListExtra("memberlist");
        if (eventinfo!=null){
            ET_NAME.setText(eventinfo.getName());
            ET_DES.setText(eventinfo.getDescription());
            position = (int)intent.getSerializableExtra("position");
            id = Integer.toString(eventinfo.getId());
            members = eventinfo.getMembers();
        }
        else {
            position = -1;
            members = new ArrayList<String>();
        }


        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,members);
        listView = (ListView)findViewById(R.id.member_list);
        listView.setAdapter(adapter);
    }

    public void cancelCreate(View view) {
        finish();
        overridePendingTransition(R.animator.zoom_enter, R.animator.zoom_exit);
    }

    public void saveGroup(View view) {


        if (members.size()==0){
            Toast.makeText(AddGroup.this, "Must have at least one member...", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(ET_NAME.getText())){
            name = "Unknown";
        }
        else{
            name = ET_NAME.getText().toString();
        }

        if (TextUtils.isEmpty(ET_DES.getText())){
            description = "Unknown";
        }
        else{
            description = ET_DES.getText().toString();
        }

        eventinfo = new Event();
        //eventinfo.setId(Integer.parseInt(id));
        eventinfo.setName(name);
        eventinfo.setDescription(description);
        eventinfo.setMembers(members);

        /*if (position==-1){
            method = "addEvent";
        }
        else
        {
            method = "updateEvent";
        }
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,name,description,id);*/

        allevents.add(eventinfo);
        Intent intent = new Intent(AddGroup.this,GroupMenu.class);
        intent.putExtra("eventlist",allevents);
        startActivity(intent);

    }

    public void deleteGroup(View view) {
        if (position!=-1){
            Intent intent = new Intent();
            intent.putExtra("position",position);
            setResult(2,intent);
            BackgroundTask backgroundTask = new BackgroundTask(this);
            method = "deleteEvent";
            backgroundTask.execute(method,id);
        }
    }

    public void setMembers(View view) {
        Intent intent = new Intent(AddGroup.this,ManageMember.class);
        intent.putExtra("memberlist",allmembers);
        intent.putExtra("member",members);
        startActivityForResult(intent,REQEST_CODE_ADD_IND);

        /*method = "updateMembers";
        eventinfo = new Event();
        eventinfo.setName(name);
        eventinfo.setDescription(description);
        eventinfo.setMembers(members);
        BackgroundTask backgroundTask = new BackgroundTask(this,AddGroup.this,eventinfo);
        backgroundTask.execute(method,name,description);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQEST_CODE_ADD_IND){
            if (resultCode==RESULT_OK) {
                members = data.getStringArrayListExtra("checkmembers");
                adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,members);
                listView = (ListView)findViewById(R.id.member_list);
                listView.setAdapter(adapter);
            }




        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}
