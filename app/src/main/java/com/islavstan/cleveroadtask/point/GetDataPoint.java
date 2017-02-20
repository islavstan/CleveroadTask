package com.islavstan.cleveroadtask.point;


import com.islavstan.cleveroadtask.model.Queries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataPoint {
    @GET("v1?")
    Call<Queries> getData(@Query("key") String key, @Query("cx") String cx, @Query("q") String q);

}

