package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import classObject.Event;
import classObject.Spending;

public class EventPage extends AppCompatActivity {

    Event event;
    ArrayList<String> allmembers;
    ArrayList<Spending> list;
    private static final int REQEST_CODE_ADD_IND = 103;
    private static final int REQEST_CODE_ADD_EVT_SP=104;
    int eventposition,spendposition;
    TextView ET_NAME;
    ListViewAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
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

        for(int i=0; i< list.size(); i++){
            list.get(i).setDate();
        }

        Collections.sort(list, new Comparator<Spending>() {
            @Override
            public int compare(Spending o1, Spending o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

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



    }

    public void backtoGroupMenu(View veiw){
        Intent intent = new Intent();
        intent.putExtra("eventposition",eventposition);
        intent.putExtra("detail",event);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void addEventSpending(View view){
        Intent addEventSpending = new Intent(EventPage.this,AddEventSpending.class);
        int id = 0;
        for (int i = 0; i<list.size(); i++){
            if (list.get(i).getId() > id) {
                id = list.get(i).getId();
            }
        }
        addEventSpending.putExtra("id",id);
        addEventSpending.putExtra("eid",event.getId());
        addEventSpending.putExtra("members",event.getMembers());
        startActivityForResult(addEventSpending,REQEST_CODE_ADD_EVT_SP);
    }

    public void editEvent(View view) {
        Intent editevent = new Intent(EventPage.this,AddGroup.class);
        editevent.putExtra("detail",event);
        editevent.putExtra("allmembers",allmembers);
        editevent.putExtra("eventposition",eventposition);
        startActivityForResult(editevent,REQEST_CODE_ADD_IND);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQEST_CODE_ADD_IND) {
            if (resultCode == RESULT_OK) {
                event = data.getParcelableExtra("detail");
                ET_NAME.setText(event.getName());

            }
            else if (resultCode ==2){
                Intent intent = new Intent();
                intent.putExtra("eventposition",eventposition);
                setResult(2,intent);
                finish();
            }
        }

        else if (requestCode == REQEST_CODE_ADD_EVT_SP){
            if (resultCode == RESULT_OK){
                Spending spending = data.getParcelableExtra("spending");
                spending.setDate();
                spendposition = (int)data.getSerializableExtra("spendposition");

                if (spendposition==-1){
                    list.add(0,spending);
                }
                else {
                    list.set(spendposition,spending);
                }
            }
            else if (resultCode==2){
                spendposition = (int)data.getSerializableExtra("spendposition");
                list.remove(spendposition);
            }
        }

        Collections.sort(list, new Comparator<Spending>() {
            @Override
            public int compare(Spending o1, Spending o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
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
                    intent.putExtra("spendposition", positions);
                    intent.putExtra("members",event.getMembers());
                    startActivityForResult(intent, REQEST_CODE_ADD_EVT_SP);
                }
            }

        });


        super.onActivityResult(requestCode, resultCode, data);

    }
    public void memberPie(View View ){
        //calculate spending for each member to a map<name,amount>

        HashMap<String, Double> map = new HashMap<>();
        for(int i = 0; i<list.size()-1;i++) {
            String key = list.get(i).getName();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + list.get(i).getAmount());
            } else {
                map.put(key, list.get(i).getAmount());
            }
        }

        Intent intent = new Intent(this,PieActivity.class);
        intent.putExtra("type", "Member");
        intent.putExtra("map",  map);
        startActivity(intent);

    }

    public void categoryPie(View view){
        //calculate spending for each category to a map<category,amount>
        HashMap<String, Double> map = new HashMap<>();
        for(int i = 0; i<list.size()-1;i++) {
            String key = list.get(i).getCategory();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + list.get(i).getAmount());
            } else {
                map.put(key, list.get(i).getAmount());
            }
        }
        Intent intent = new Intent(this,PieActivity.class);
        intent.putExtra("type", "Category");
        intent.putExtra("map",  map);
        startActivity(intent);

    }



}
