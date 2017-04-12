package com.example.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BarActivity extends AppCompatActivity {

    BarChart barChart;
    ArrayList<String> dates;
    ArrayList<BarEntry> barEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        barChart = (BarChart)findViewById(R.id.idBarChart);

    }

    public void createGraph(){}
}
