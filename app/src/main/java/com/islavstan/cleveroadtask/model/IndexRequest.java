package com.islavstan.cleveroadtask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class IndexRequest {
    @SerializedName("request")
    List<RequestData> requestDatas;

    public List<RequestData> getRequestDatas() {
        return requestDatas;
    }
}
