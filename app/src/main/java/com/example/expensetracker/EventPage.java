package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.AdapterView;
=======
>>>>>>> parent of 56b9b52... fix add event bug
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import classObject.Event;
import classObject.Spending;
=======

import java.util.ArrayList;

import classObject.Event;
>>>>>>> parent of 56b9b52... fix add event bug

public class EventPage extends AppCompatActivity {

    Event event;
    ArrayList<String> allmembers;
<<<<<<< HEAD
    ArrayList<Spending> list;
    private static final int REQEST_CODE_ADD_IND = 103;
    private static final int REQEST_CODE_ADD_EVT_SP=104;
    int eventposition,spendposition;
    TextView ET_NAME;
    ListViewAdapter adapter;
    ListView listView;
=======
    private static final int REQEST_CODE_ADD_IND = 103;
    int position;
    TextView ET_NAME;

>>>>>>> parent of 56b9b52... fix add event bug

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
<<<<<<< HEAD
        Intent intent = getIntent();
        event = intent.getParcelableExtra("detail");
        allmembers = intent.getStringArrayListExtra("allmembers");
        eventposition = (int) intent.getSerializableExtra("eventposition");
        ET_NAME  = (TextView)findViewById(R.id.event_label);
        ET_NAME.setText(event.getName());
        try {
            EventSpendingBackgroundTask eventSpendingBackgroundTask = new EventSpendingBackgroundTask(EventPage.this);
            list = eventSpendingBackgroundTask.execute("downEventSpend", Integer.toString(event.getId())).get();
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Collections.reverse(list);
        adapter=new ListViewAdapter(this, list);
        listView = (ListView) findViewById(R.id.event_spending);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int positions, long id)
            {
                int pos=positions+1;
                if (pos!=list.size()) {
                    Toast.makeText(EventPage.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EventPage.this, AddEventSpending.class);
                    intent.putExtra("detail", list.get(positions));
                    intent.putExtra("members",event.getMembers());
                    intent.putExtra("spendposition", positions);
                    startActivityForResult(intent, REQEST_CODE_ADD_EVT_SP);
                }
            }

        });


=======

        Intent intent = getIntent();
        event = intent.getParcelableExtra("detail");
        allmembers = intent.getStringArrayListExtra("allmembers");
        position = (int) intent.getSerializableExtra("position");
        ET_NAME  = (TextView)findViewById(R.id.event_label);
        ET_NAME.setText(event.getName());
>>>>>>> parent of 56b9b52... fix add event bug

    }

    public void backtoGroupMenu(View veiw){
        Intent intent = new Intent();
<<<<<<< HEAD
        intent.putExtra("groupposition",eventposition);
        intent.putExtra("detail",event);
=======
        intent.putExtra("position",position);
        intent.putExtra("newevent",event);
>>>>>>> parent of 56b9b52... fix add event bug
        setResult(RESULT_OK,intent);
        finish();
    }

    public void addEventSpending(View view){
        Intent addEventSpending = new Intent(EventPage.this,AddEventSpending.class);
<<<<<<< HEAD
        addEventSpending.putExtra("id",list.get(0).getId());
        addEventSpending.putExtra("eid",event.getId());
        addEventSpending.putExtra("members",event.getMembers());
        startActivityForResult(addEventSpending,REQEST_CODE_ADD_EVT_SP);
=======
        startActivity(addEventSpending);
>>>>>>> parent of 56b9b52... fix add event bug
    }

    public void editEvent(View view) {
        Intent editevent = new Intent(EventPage.this,AddGroup.class);
        editevent.putExtra("detail",event);
        editevent.putExtra("allmembers",allmembers);
<<<<<<< HEAD
        editevent.putExtra("addposition",eventposition);
=======
        editevent.putExtra("position",position);
>>>>>>> parent of 56b9b52... fix add event bug
        startActivityForResult(editevent,REQEST_CODE_ADD_IND);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQEST_CODE_ADD_IND) {
            if (resultCode == RESULT_OK) {
<<<<<<< HEAD
                event = data.getParcelableExtra("detail");
                ET_NAME.setText(event.getName());
=======
                event = data.getParcelableExtra("newevent");
>>>>>>> parent of 56b9b52... fix add event bug

            }
            else if (resultCode ==2){
                Intent intent = new Intent();
<<<<<<< HEAD
                intent.putExtra("eventposition",eventposition);
=======
                intent.putExtra("position",position);
>>>>>>> parent of 56b9b52... fix add event bug
                setResult(2,intent);
                finish();
            }
        }
<<<<<<< HEAD

        else if (requestCode == REQEST_CODE_ADD_EVT_SP){
            if (resultCode == RESULT_OK){
                Spending spending = data.getParcelableExtra("spending");
                spendposition = (int)data.getSerializableExtra("eventposition");

                if (spendposition==-1){
                    list.add(0,spending);
                }
                else {
                    list.set(spendposition,spending);
                }
            }
            else if (requestCode==2){
                spendposition = (int)data.getSerializableExtra("eventposition");
                list.remove(spendposition);
            }
        }

        adapter=new ListViewAdapter(this, list);
        listView = (ListView) findViewById(R.id.event_spending);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int positions, long id)
            {
                int pos=positions+1;
                if (pos!=list.size()) {
                    Toast.makeText(EventPage.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EventPage.this, AddEventSpending.class);
                    intent.putExtra("detail", list.get(positions));
                    intent.putExtra("spendposition", positions);
                    intent.putExtra("members",event.getMembers());
                    startActivityForResult(intent, REQEST_CODE_ADD_EVT_SP);
                }
            }

        });


=======
>>>>>>> parent of 56b9b52... fix add event bug
            super.onActivityResult(requestCode, resultCode, data);

    }
}
