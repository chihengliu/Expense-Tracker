package com.example.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.graphics.Color;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.lang.Double;
import android.content.Intent;

public class PieActivity extends AppCompatActivity {

    private static String TAG = "PieActivity";
    private float[] yData;
    private String[] xData;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        HashMap<String,Double> map = (HashMap<String, Double>) intent.getSerializableExtra("map");

        yData = new float[map.size()];
        xData = new String[map.size()];
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            xData[i] = mentry.getKey().toString();

            yData[i] = Float.parseFloat(mentry.getValue().toString());
            i++;
        }

        setContentView(R.layout.activity_pie);
        Log.d(TAG, "onCreate: starting to create chart");
        pieChart = (PieChart) findViewById(R.id.idPieChart);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Thug Life");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);

    addDataSet(pieChart);
}

    private void addDataSet(PieChart pieChart){
        Log.d(TAG, "addDataSet: started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i=0; i<yData.length;i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }
        for (int i=0; i<xData.length;i++) {
            xEntrys.add(xData[i]);
        }
        //create data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys,"Spending");
        pieDataSet.setSliceSpace(1);
        pieDataSet.setValueTextSize(12);

        //add colors
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.CYAN);
        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = this.pieChart.getLegend();

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        this.pieChart.setData(pieData);
        this.pieChart.invalidate();
    }
}
