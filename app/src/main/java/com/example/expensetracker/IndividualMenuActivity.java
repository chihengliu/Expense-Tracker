package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static classObject.Constants.FIRST_COLUMN;
import static classObject.Constants.SECOND_COLUMN;

public class IndividualMenuActivity extends AppCompatActivity {
    private ArrayList<HashMap<String, String>> list;
    private static final int REQEST_CODE_ADD_IND = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_menu);
        list = new ArrayList<HashMap<String,String>>();
    }

    public void mainMenu(View view) {
        Intent backToMain = new Intent(IndividualMenuActivity.this,MainMenu.class);
        startActivity(backToMain);
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
                ArrayList<String> info = data.getStringArrayListExtra("spinfo");
                HashMap<String,String> temp=new HashMap<String, String>();
                temp.put(FIRST_COLUMN, info.get(3));
                temp.put(SECOND_COLUMN, info.get(2));
                temp.put("Third",info.get(1));
                list.add(temp);
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
                        Intent intent = new Intent(IndividualMenuActivity.this,individual_detail.class);
                        intent.putExtra("detail",list.get(position));
                        startActivity(intent);
                    }

                });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
