package com.example.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

//Reference
//https://github.com/PhilJay/MPAndroidChart/tree/master/MPChartLib/src/main/java/com/github/mikephil/charting

public class BarActivity extends AppCompatActivity {

    BarChart barChart;
    ArrayList<String> dates;
    ArrayList<BarEntry> barEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        barChart = (BarChart)findViewById(R.id.idBarChart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0,44f,"May"));
        barEntries.add(new BarEntry(1,88f,"April"));
        //new BarEntry()

        BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");
        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("April");
        theDates.add("May");

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData barData = new BarData(dataSets);
        barChart.setData(barData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }

    public void createGraph(){}
}
