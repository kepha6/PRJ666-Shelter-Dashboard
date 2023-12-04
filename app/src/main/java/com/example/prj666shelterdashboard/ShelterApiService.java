package com.example.prj666shelterdashboard;
import com.example.prj666shelterdashboard.ui.Shelter;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShelterApiService {
    String BASE_URL = "https://shelterapi.furqwanserver.ca/";
    @GET("shelters")
    Call<List<Shelter>> getShelters(@Query("limit") int limit);

    @GET("/analytics/cities")
    Call<ResponseBody> getCityAnalytics();

    @GET("/analytics/occupancy")
    Call<ResponseBody> getOccupancyAnalytics();

//
}

