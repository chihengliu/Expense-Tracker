package com.example.expensetracker;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import static classObject.Constants.FIRST_COLUMN;
import static classObject.Constants.SECOND_COLUMN;

import java.util.ArrayList;
import classObject.Spending;

public class IndividualMenuActivity extends AppCompatActivity {
    //private ArrayList<HashMap<String, String>> list;
    private ArrayList<Spending> list;
    private static final int REQEST_CODE_ADD_IND = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_menu);
        //list = new ArrayList<HashMap<String,String>>();

        list = new ArrayList<Spending>();

    }

    public void mainMenu(View view) {
        finish();
        overridePendingTransition(R.animator.zoom_enter,R.animator.zoom_exit);
    }

    public void addIndividualEvent(View view){
        Intent addIndividualWindow = new Intent(IndividualMenuActivity.this,AddIndividual.class);
        startActivityForResult(addIndividualWindow,REQEST_CODE_ADD_IND);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQEST_CODE_ADD_IND){
            if (resultCode==RESULT_OK){
                Spending info = data.getParcelableExtra("spinfo");
                //HashMap<String,String> temp=new HashMap<String, String>();
                //temp.put(FIRST_COLUMN, info.get(3));
                //temp.put(SECOND_COLUMN, info.get(2));
                //temp.put("Third",info.get(1));
                int position = (int)data.getSerializableExtra("position");
                if (position==-1) {
                    list.add(info);
                }
                else {
                    list.set(position,info);
                }
                ListViewAdapter adapter=new ListViewAdapter(this, list);
                ListView listView = (ListView) findViewById(R.id.spendingList);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
                    {
                        int pos=position+1;
                        Toast.makeText(IndividualMenuActivity.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(IndividualMenuActivity.this,AddIndividual.class);
                        intent.putExtra("detail",list.get(position));
                        intent.putExtra("position",position);
                        startActivityForResult(intent,REQEST_CODE_ADD_IND);
                    }

                });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
