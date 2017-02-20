package com.islavstan.cleveroadtask.model;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Query;

/**
 * Created by islav on 20.02.2017.
 */
public class QueriesData {
    @SerializedName("title")
    String title;
    @SerializedName("pagemap")
     Pagemap pagemap;

    public String getTitle() {
        return title;
    }

    public Pagemap getPagemap() {
        return pagemap;
    }
}
