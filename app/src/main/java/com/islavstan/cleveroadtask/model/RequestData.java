package com.islavstan.cleveroadtask.model;

import com.google.gson.annotations.SerializedName;



public class RequestData {
    @SerializedName("startIndex")
    int startIndex;

    public int getStartIndex() {
        return startIndex;
    }
}
