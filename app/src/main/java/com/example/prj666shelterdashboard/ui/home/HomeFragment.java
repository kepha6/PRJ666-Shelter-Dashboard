    package com.example.prj666shelterdashboard.ui.home;

    import android.graphics.Color;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.ViewModelProvider;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

    import com.example.prj666shelterdashboard.R;
    import com.example.prj666shelterdashboard.RetrofitClient;
    import com.example.prj666shelterdashboard.databinding.FragmentHomeBinding;
    import com.example.prj666shelterdashboard.ui.CityAnalytics;
    import com.example.prj666shelterdashboard.ui.OccupancyAnalytics;
    import com.example.prj666shelterdashboard.ui.Shelter;
    import com.github.mikephil.charting.charts.BarChart;
    import com.github.mikephil.charting.components.Legend;
    import com.github.mikephil.charting.components.LegendEntry;
    import com.github.mikephil.charting.components.XAxis;
    import com.github.mikephil.charting.data.BarData;
    import com.github.mikephil.charting.data.BarDataSet;
    import com.github.mikephil.charting.data.BarEntry;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.List;

    import okhttp3.ResponseBody;
    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class HomeFragment extends Fragment {

        private FragmentHomeBinding binding;
        private RecyclerView recyclerView;
        private ShelterAdapter shelterAdapter;
        private List<Shelter> shelters;
        private OccupancyAnalytics occupancyAnalytics;
        private List<CityAnalytics> cityAnalyticsList;
        private SwipeRefreshLayout swipeRefreshLayout;


        // variable for our bar chart
        BarChart barChart;
        // variable for our bar data.
        BarData barData;
        // variable for our bar data set.
        BarDataSet barDataSet;
        // array list for storing entries.
        ArrayList<BarEntry> barEntriesArrayList;

        TextView TotalAvailableBeds;
        TextView TotalOccupiedBeds;
        TextView TotalUnallocatedBeds;
        Legend legend;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            HomeViewModel homeViewModel =
                    new ViewModelProvider(this).get(HomeViewModel.class);

            binding = FragmentHomeBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            recyclerView = root.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            //Make Shelter List and call API
            shelters = new ArrayList<>();
            getShelters();
            shelterAdapter = new ShelterAdapter(shelters, new ShelterAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // Handle item click (e.g., show more info about the shelter)
                    Shelter shelter = shelters.get(position);
                    // Implement the action to show more info about the shelter.
                }
            });
            recyclerView.setAdapter(shelterAdapter);

            // initializing variable for bar chart.
            barChart = root.findViewById(R.id.idBarChart);

            // calling method to get bar entries.
            getBarEntries();
            barChart.setNoDataText("Swipe Down to Refresh");

            // Check if barEntriesArrayList is not null before creating BarDataSet
            if (barEntriesArrayList != null && !barEntriesArrayList.isEmpty()) {

                // creating a new bar data set.
                barDataSet = new BarDataSet(barEntriesArrayList, "Home Shelters");

                // adding color to our bar data set.
                barDataSet.setColors(new int[]{Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN});

                // setting text color.
                barDataSet.setValueTextColor(Color.BLACK);

                // setting text size
                barDataSet.setValueTextSize(16f);

                // setting other configurations as needed

                // creating a new bar data and passing our bar data set.
                barData = new BarData(barDataSet);

                // below line is to set data to our bar chart.
                barChart.setData(barData);
            } else {
                Log.e("HomeFragment", "Bar entries list is null or empty");
            }

            barChart.getDescription().setEnabled(false);
            XAxis xAxis = barChart.getXAxis();
            xAxis.setDrawGridLines(false);
            xAxis.setDrawLabels(false);

            //Get Occupancy Shelter Information
            getOccupancyAnalytics();
            TotalAvailableBeds = root.findViewById(R.id.TotalBedsDisplay);
            TotalOccupiedBeds = root.findViewById(R.id.TotalOccupiedDisplay);
            TotalUnallocatedBeds = root.findViewById(R.id.TotalUnallocatedDisplay);

            legend = barChart.getLegend();

            // Initialize SwipeRefreshLayout
            swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);
            swipeRefreshLayout.setOnRefreshListener(() -> refreshFragment());


            return root;
        }

        //Get Occupancy Information
        private void getOccupancyAnalytics() {
            Call<ResponseBody> call = RetrofitClient.getInstance().getMyApi().getOccupancyAnalytics();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d("Home Occupancy Analytic Fragment", "Successful API Call");
                    if (response.isSuccessful()){
                        try {
                            // Parse the response body as a JSON object
                            JSONObject jsonObject = new JSONObject(response.body().string());

                            // Extract data from the JSON object
                            int totalBeds = jsonObject.getInt("totalBeds");
                            int availableBeds = jsonObject.getInt("avaliableBeds");
                            int occupiedBeds = jsonObject.getInt("occupiedBed");
                            int unavailableBeds = jsonObject.getInt("unavaliableBeds");

                            // Create an OccupancyAnalytics object with the extracted data
                            occupancyAnalytics = new OccupancyAnalytics(totalBeds, availableBeds, occupiedBeds, unavailableBeds);

                            // Update your UI or perform any actions with occupancyAnalytics here
                            TotalAvailableBeds.setText(String.valueOf(occupancyAnalytics.getAvailableBeds()));
                            TotalOccupiedBeds.setText(String.valueOf(occupancyAnalytics.getOccupiedBeds()));
                            TotalUnallocatedBeds.setText(String.valueOf(occupancyAnalytics.getUnavailableBeds()));

                        } catch (Exception e) {
                            Log.e("Home Occupancy Analytic Fragment", "Error parsing JSON: " + e.getMessage());
                        }

                    }else{
                        Log.d("Home Occupancy Analytic Fragment", "Unsuccessful API Call");
                    }

                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Home Occupancy Analytic Fragment", "Error: " + t.getMessage());

                }
            });
        }
        //Get Shelters for recycler view
        private void getShelters() {
            // Make the asynchronous API call
            Call<List<Shelter>> call = RetrofitClient.getInstance().getMyApi().getShelters(10);
            call.enqueue(new Callback<List<Shelter>>() {
                @Override
                public void onResponse(Call<List<Shelter>> call, Response<List<Shelter>> response) {
                    if (response.isSuccessful()) {
                        Log.d("Home Fragment", "Successful API Call");
                        shelters = response.body();
                        shelterAdapter = new ShelterAdapter(shelters, new ShelterAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Log.d("Home Fragment", String.valueOf(position));
                            }
                        });
                        recyclerView.setAdapter(shelterAdapter);

                    } else {
                        Log.e("Home Fragment Response", "Error: " + response.code());
                    }
                }
                @Override
                public void onFailure(Call<List<Shelter>> call, Throwable t) {
                    Log.e("Home Fragment", "Error: " + t.getMessage());
                }
            });

        }
        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
        private void getBarEntries() {
            cityAnalyticsList = new ArrayList<>();
            Call<ResponseBody> call = RetrofitClient.getInstance().getMyApi().getCityAnalytics();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        Log.d("Home City Analytic Fragment", "Successful API Call");

                        try {
                            String responseBodyString = response.body().string(); // Convert ResponseBody to string
                            JSONArray jsonArray = new JSONArray(responseBodyString);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //Log.d("Home bar Fragment", String.valueOf(jsonObject));
                                String location = jsonObject.optString("location", "");

                                // Only add the object if location is not null
                                if (location != null && !location.isEmpty()) {
                                    int totalUnoccupiedBeds = jsonObject.optInt("totalUnoccupiedBeds", 0);
                                    int totalOccupiedRooms = jsonObject.optInt("totalOccupiedRooms", 0);

                                    CityAnalytics cityAnalytics = new CityAnalytics(location, totalUnoccupiedBeds, totalOccupiedRooms);
                                    cityAnalyticsList.add(cityAnalytics);
                                }
                            }

                            // Sort cityAnalyticsList based on totalOccupiedRooms in descending order
                            Collections.sort(cityAnalyticsList, new Comparator<CityAnalytics>() {
                                @Override
                                public int compare(CityAnalytics o1, CityAnalytics o2) {
                                    return Integer.compare(o2.getTotalOccupiedRooms(), o1.getTotalOccupiedRooms());
                                }
                            });

                            // Now that we have the data, update the legend entries
                            //updateLegendEntries();

                            // Also, create the bar entries
                            createBarEntries();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        Log.d("Home City Analytic Fragment", "Unsuccessful API Call");
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Home City Analytic Fragment", "Error: " + t.getMessage());

                }
            });

        }
        private int getColorForIndex(int index) {
            // You can implement a logic to assign different colors based on the index
            // For example, you can use an array of predefined colors
            int[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
            return colors[index % colors.length];
        }
        private void createBarEntries() {
            // creating a new array list
            barEntriesArrayList = new ArrayList<>();

            // Adding new entry to our array list with bar
            // entry and passing x and y axis value to it.
            if (cityAnalyticsList != null) {
                for (int i = 0; i < cityAnalyticsList.size(); i++) {
                    barEntriesArrayList.add(new BarEntry(i + 1, cityAnalyticsList.get(i).getTotalOccupiedRooms()));
                }
            }
        }
        private void updateLegendEntries() {

            List<LegendEntry> legendEntries = new ArrayList<>();

            for (int i = 0; i < cityAnalyticsList.size(); i++) {
                String location = cityAnalyticsList.get(i).getLocation();
                // You can customize the color based on your preference or use a color array
                int color = getColorForIndex(i);
                legendEntries.add(new LegendEntry(location, Legend.LegendForm.DEFAULT, 10f, 2f, null, color));
                Log.d("Home City Analytic Fragment", "Size: " +legendEntries.size());

            }

            // Update legend entries based on City Analytics data
            legend.setCustom(new LegendEntry[]{legendEntries.get(0),legendEntries.get(1),legendEntries.get(2),legendEntries.get(3)});
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            legend.setDrawInside(false);
            legend.setXOffset(5f);

            legend = barChart.getLegend();
        }

        private LegendEntry[] getLegendEntriesArray() {
            List<LegendEntry> legendEntries = new ArrayList<>();

            for (int i = 0; i < cityAnalyticsList.size(); i++) {
                String location = cityAnalyticsList.get(i).getLocation();
                // You can customize the color based on your preference or use a color array
                int color = getColorForIndex(i);
                legendEntries.add(new LegendEntry(location, Legend.LegendForm.DEFAULT, 10f, 2f, null, color));
                Log.d("Home City Analytic Fragment", "Size: " +legendEntries.size());

            }

            Log.d("Home City Analytic Fragment", "Legend: " + legendEntries.get(0));

            return legendEntries.toArray(new LegendEntry[]{legendEntries.get(0),legendEntries.get(1),legendEntries.get(2),legendEntries.get(3)});
        }
        // Method to refresh the current fragment
        public void refreshFragment() {
            // Detach the fragment
            getParentFragmentManager().beginTransaction().detach(this).commit();

            // Attach the fragment again
            getParentFragmentManager().beginTransaction().attach(this).commit();
            // Stop the refreshing animation
            swipeRefreshLayout.setRefreshing(false);

            updateLegendEntries();
        }
    }