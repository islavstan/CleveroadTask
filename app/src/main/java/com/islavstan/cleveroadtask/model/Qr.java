package com.islavstan.cleveroadtask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Qr {
    @SerializedName("request")
    List<RequestData> requestDatas;

    public List<RequestData> getRequestDatas() {
        return requestDatas;
    }
}
