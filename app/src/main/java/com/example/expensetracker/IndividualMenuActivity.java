package com.example.expensetracker;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.text.ParseException;


import classObject.Spending;
import java.util.Comparator;

import java.util.Calendar;

public class IndividualMenuActivity extends AppCompatActivity {
    //private ArrayList<HashMap<String, String>> list;
    private ArrayList<Spending> list;
    private static final int REQEST_CODE_ADD_IND = 100;
    ListViewAdapter adapter;
    ListView listView;

    int weekFlag = 0;
    private ArrayList<Spending> tempList = new ArrayList<Spending>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_menu);
        Intent intent2 = getIntent();
        list = intent2.getParcelableArrayListExtra("list");
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
        listView = (ListView) findViewById(R.id.spendingList);
        listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
                if (pos!=list.size()) {
                    Toast.makeText(IndividualMenuActivity.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(IndividualMenuActivity.this, AddIndividual.class);
                    intent.putExtra("detail", list.get(position));
                    intent.putExtra("position", list.get(position).getId());
                    intent.putExtra("name", list.get(position).getName());
                    startActivityForResult(intent, REQEST_CODE_ADD_IND);
                }
            }

        });

    }

    public void mainMenu(View view) {
        finish();
        overridePendingTransition(R.animator.zoom_enter,R.animator.zoom_exit);
    }

    public void weeklySpending(View view) {
        if (weekFlag == 0){
            Date presentDate = new Date();
            int noOfDays = -7; //i.e two weeks
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(presentDate);
            calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
            Date previousDate = calendar.getTime();

            for (int i=0; i<list.size(); i++ ){
                if (presentDate.after(list.get(i).getDate()) && previousDate.before(list.get(i).getDate())){
                    tempList.add(list.get(i));
                }
            }
            tempList.add(list.get(list.size()-1));

            weekFlag = 1;

            adapter=new ListViewAdapter(this, tempList);
            listView = (ListView) findViewById(R.id.spendingList);
            listView.setAdapter(adapter);
            //adapter.notifyDataSetChanged();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
                {
                    int pos=position+1;
                    if (pos!=list.size()) {
                        Toast.makeText(IndividualMenuActivity.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(IndividualMenuActivity.this, AddIndividual.class);
                        intent.putExtra("detail", tempList.get(position));
                        intent.putExtra("position", tempList.get(position).getId());
                        intent.putExtra("name", tempList.get(position).getName());
                        startActivityForResult(intent, REQEST_CODE_ADD_IND);
                    }
                }

            });
        }
        else{
            tempList.removeAll(tempList);
            weekFlag = 0;
            adapter=new ListViewAdapter(this, list);
            listView = (ListView) findViewById(R.id.spendingList);
            listView.setAdapter(adapter);
            //adapter.notifyDataSetChanged();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
                {
                    int pos=position+1;
                    if (pos!=list.size()) {
                        Toast.makeText(IndividualMenuActivity.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(IndividualMenuActivity.this, AddIndividual.class);
                        intent.putExtra("detail", list.get(position));
                        intent.putExtra("position", list.get(position).getId());
                        intent.putExtra("name", list.get(position).getName());
                        startActivityForResult(intent, REQEST_CODE_ADD_IND);
                    }
                }

            });
        }


    }

    public void addIndividualEvent(View view){
        Intent addIndividualWindow = new Intent(IndividualMenuActivity.this,AddIndividual.class);
        int id = 0;
        for (int i = 0; i<list.size(); i++){
            if (list.get(i).getId() > id) {
                id = list.get(i).getId();
            }
        }
        addIndividualWindow.putExtra("id",id);

        addIndividualWindow.putExtra("name",list.get(0).getName());
        startActivityForResult(addIndividualWindow,REQEST_CODE_ADD_IND);
        //startActivity(addIndividualWindow);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQEST_CODE_ADD_IND){
            if (resultCode==RESULT_OK) {
                Spending info = data.getParcelableExtra("spinfo");
                info.setDate();

                int position = (int) data.getSerializableExtra("position");

                if (position == -1) {
                    list.add(0, info);
                } else {
                    for (int i =0 ; i<list.size(); i++){
                        if(position == list.get(i).getId()){
                            list.set(i, info);
                            return;
                        }
                    }
                }

                weekFlag = 0;

                Collections.sort(list, new Comparator<Spending>() {
                    @Override
                    public int compare(Spending o1, Spending o2) {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                });
                Collections.reverse(list);

            }
            else if (resultCode == 2)
            {
                int position = (int) data.getSerializableExtra("position");
                for (int i =0 ; i<list.size(); i++){
                    if(position == list.get(i).getId()){
                        list.remove(position);
                        return;
                    }
                }
            }


//            adapter = new ListViewAdapter(this, list);
//            //adapter.updateList(list);
//            adapter.notifyDataSetChanged();
//            listView.setAdapter(adapter);

            adapter=new ListViewAdapter(this, tempList);
            listView = (ListView) findViewById(R.id.spendingList);
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
                {
                    int pos=position+1;
                    if (pos!=list.size()) {
                        Intent intent = new Intent(IndividualMenuActivity.this, AddIndividual.class);
                        intent.putExtra("detail", list.get(position));
                        intent.putExtra("position", list.get(position).getId());
                        intent.putExtra("name", list.get(position).getName());
                        startActivityForResult(intent, REQEST_CODE_ADD_IND);
                    }
                }

            });


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
