package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import classObject.Event;

public class EventPage extends AppCompatActivity {

    Event event;
    ArrayList<String> allmembers;
    private static final int REQEST_CODE_ADD_IND = 103;
    int position;
    TextView ET_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        Intent intent = getIntent();
        event = intent.getParcelableExtra("detail");
        allmembers = intent.getStringArrayListExtra("allmembers");
        position = (int) intent.getSerializableExtra("position");
        ET_NAME  = (TextView)findViewById(R.id.event_label);
        ET_NAME.setText(event.getName());

    }

    public void backtoGroupMenu(View veiw){
        Intent intent = new Intent();
        intent.putExtra("position",position);
        intent.putExtra("newevent",event);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void addEventSpending(View view){
        Intent addEventSpending = new Intent(EventPage.this,AddEventSpending.class);
        startActivity(addEventSpending);
    }

    public void editEvent(View view) {
        Intent editevent = new Intent(EventPage.this,AddGroup.class);
        editevent.putExtra("detail",event);
        editevent.putExtra("allmembers",allmembers);
        editevent.putExtra("position",position);
        startActivityForResult(editevent,REQEST_CODE_ADD_IND);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQEST_CODE_ADD_IND) {
            if (resultCode == RESULT_OK) {
                event = data.getParcelableExtra("newevent");

            }
            else if (resultCode ==2){
                Intent intent = new Intent();
                intent.putExtra("position",position);
                setResult(2,intent);
                finish();
            }
        }
            super.onActivityResult(requestCode, resultCode, data);

    }
}
