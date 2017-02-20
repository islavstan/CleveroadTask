package com.islavstan.cleveroadtask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Queries {
    @SerializedName("items")
  List<QueriesData>queriesDataList;

    public List<QueriesData> getQueriesDataList() {
        return queriesDataList;
    }
}
