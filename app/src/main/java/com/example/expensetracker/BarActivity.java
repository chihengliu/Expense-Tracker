package com.example.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ArrayList<Double> amountArray = (ArrayList<Double>) intent.getSerializableExtra("amountArray");


        setContentView(R.layout.activity_bar);

        barChart = (BarChart)findViewById(R.id.idBarChart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0;i<7;i++){

            float f = Float.parseFloat(amountArray.get(i).toString());
            barEntries.add(new BarEntry(i,f));

        }

        //new BarEntry()

        BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");
        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("April");
        theDates.add("May");

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData barData = new BarData(dataSets);
        barData.setValueTextSize(14);
        barChart.setData(barData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setDrawGridBackground(false);
        Description d = barChart.getDescription();
        d.setText("Spending in past 7 days");
        d.setTextSize(20);
        d.setXOffset(10);
        d.setYOffset(-12);
    }

    public void createGraph(){}
}
