package com.example.expensetracker;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import classObject.Event;

public class ManageMember extends AppCompatActivity {
    ArrayList<String> members;
    ArrayList<String> allmembers;
    Event event;
    ArrayAdapter<String> adapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_member);
        Intent intent = getIntent();
        allmembers = intent.getStringArrayListExtra("memberlist");
        members = intent.getStringArrayListExtra("member");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,allmembers);
        listView = (ListView)findViewById(R.id.allmember);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(adapter);
        if (members.size()!=0) {
            for (int i=1; i<allmembers.size();i++){
                if (members.contains(allmembers.get(i))){
                    listView.setItemChecked(i,true);
                }
            }
        }


    }

    public void onListItemClick(ListView parent, View v,int position,long id){
        CheckedTextView item = (CheckedTextView) v;
        Toast.makeText(this, allmembers.get(position) + " checked : " +
                item.isChecked(), Toast.LENGTH_SHORT).show();
    }

    public void save (View view) {
        members = new ArrayList<String>();
        SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
        for (int i=0;i<allmembers.size();i++){
            if (sparseBooleanArray.get(i)){
                members.add(allmembers.get(i));
            }
        }
        Intent intent = new Intent(ManageMember.this,AddGroup.class);
        intent.putExtra("checkmembers",members);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void cancel (View view) {
        finish();
    }
}
