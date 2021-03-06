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
import java.util.concurrent.ExecutionException;

import classObject.Event;


public class AddGroup extends AppCompatActivity {
    EditText ET_NAME,ET_DES;
    String name, description,id;
    ArrayList<String> members;
    Event eventinfo;
    int eventposition;
    ArrayAdapter<String> adapter;
    ListView listView;
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
        allmembers = intent.getStringArrayListExtra("allmembers");
        if (eventinfo!=null){
            ET_NAME.setText(eventinfo.getName());
            ET_DES.setText(eventinfo.getDescription());
            eventposition = (int)intent.getSerializableExtra("eventposition");
            id = Integer.toString(eventinfo.getId());
            members = eventinfo.getMembers();
        }
        else {
            eventposition = -1;
            members = new ArrayList<String>();
            id = "0";
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
        eventinfo.setId(Integer.parseInt(id));
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


        BackgroundTask backgroundTask = new BackgroundTask(this, eventinfo.getMembers());
        if (eventposition == -1){
            try {
                id = backgroundTask.execute("addEventAndMember", eventinfo.getName(), eventinfo.getDescription()).get();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (ExecutionException e){
                e.printStackTrace();
            }
        }
        else {
            backgroundTask.execute("updateEventAndMember", Integer.toString(eventinfo.getId()), eventinfo.getName(), eventinfo.getDescription());
        }
        eventinfo.setId(Integer.parseInt(id));
        Intent intent = new Intent(AddGroup.this,GroupMenu.class);
        intent.putExtra("detail",eventinfo);
        intent.putExtra("eventposition",eventposition);

        setResult(RESULT_OK,intent);
        finish();

    }

    public void deleteGroup(View view) {
        if (eventposition!=-1){
            Intent intent = new Intent();
            intent.putExtra("eventposition",eventposition);
            setResult(2,intent);
            BackgroundTask backgroundTask = new BackgroundTask(this);
            String method = "deleteEvent";
            backgroundTask.execute(method,id);
            finish();
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
