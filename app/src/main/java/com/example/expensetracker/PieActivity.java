package com.example.expensetracker;

import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.graphics.Color;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.lang.Double;
import android.content.Intent;
import android.view.View;

//Reference
//https://github.com/PhilJay/MPAndroidChart/tree/master/MPChartLib/src/main/java/com/github/mikephil/charting

public class PieActivity extends AppCompatActivity {

    private static String TAG = "PieActivity";
    private float[] yData;
    private String[] xData;
    PieChart pieChart;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        HashMap<String, Double> map = (HashMap<String, Double>) intent.getSerializableExtra("map");
        type = intent.getStringExtra("type");
        yData = new float[map.size()];
        xData = new String[map.size()];
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            xData[i] = mentry.getKey().toString();

            yData[i] = Float.parseFloat(mentry.getValue().toString());
            i++;
        }

        setContentView(R.layout.activity_pie);
        Log.d(TAG, "onCreate: starting to create chart");

        //get the chart and set properties
        pieChart = (PieChart) findViewById(R.id.idPieChart);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(7);
        pieChart.setTransparentCircleRadius(10);
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(12f);

        //get Description and set it
        Description d = pieChart.getDescription();
        d.setText("Spending by "+type);
        d.setTextSize(20);
        d.setXOffset(10);
        d.setYOffset(20);
        addDataSet(pieChart);
    }



    private void addDataSet(PieChart pieChart){
        Log.d(TAG, "addDataSet: started");
        ArrayList<PieEntry> Entrys = new ArrayList<>();

        for (int i=0; i<yData.length;i++) {
            Entrys.add(new PieEntry(yData[i], xData[i]));
        }

        //create data set

        PieDataSet pieDataSet = new PieDataSet(Entrys,"Spending");
        pieDataSet.setSliceSpace(3);

        //add colors
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c: ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        pieDataSet.setColors(colors);

        //customize legends
        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setYOffset(10);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setXEntrySpace(7);
        l.setYEntrySpace(7);
        l.setFormSize(20);


        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(20f);
        this.pieChart.setData(pieData);
        this.pieChart.invalidate();
    }
}
