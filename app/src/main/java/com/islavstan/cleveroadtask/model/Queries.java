package com.islavstan.cleveroadtask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Queries {
    @SerializedName("items")
  List<QueriesData>queriesDataList;

   @SerializedName("queries")
   IndexRequest indexRequest;

    public IndexRequest getIndexRequest() {
        return indexRequest;
    }

    public List<QueriesData> getQueriesDataList() {
        return queriesDataList;
    }
}
