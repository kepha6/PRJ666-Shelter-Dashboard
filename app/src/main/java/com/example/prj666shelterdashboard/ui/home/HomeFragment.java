package com.example.prj666shelterdashboard.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prj666shelterdashboard.R;
import com.example.prj666shelterdashboard.databinding.FragmentHomeBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.data.BarEntry;


import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // initializing variable for bar chart.
        barChart = root.findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Home Shelters");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(new int[]{Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN});

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        YAxis leftAxis = barChart.getAxisLeft();
        YAxis rightAxis = barChart.getAxisRight();


        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);

        Legend legend = barChart.getLegend();
        LegendEntry l1 = new LegendEntry("Toronto",Legend.LegendForm.DEFAULT,10f,2f,null, Color.RED);
        LegendEntry l2 = new LegendEntry("Brampton",Legend.LegendForm.DEFAULT,10f,2f,null, Color.BLUE);
        LegendEntry l3 = new LegendEntry("Markham",Legend.LegendForm.DEFAULT,10f,2f,null, Color.YELLOW);
        LegendEntry l4 = new LegendEntry("Scarborough",Legend.LegendForm.DEFAULT,10f,2f,null, Color.GREEN);
        legend.setCustom(new LegendEntry[]{l1,l2,l3,l4});
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1, -4));
        barEntriesArrayList.add(new BarEntry(2, 6));
        barEntriesArrayList.add(new BarEntry(3, 8));
        barEntriesArrayList.add(new BarEntry(4, 10));
    }
}