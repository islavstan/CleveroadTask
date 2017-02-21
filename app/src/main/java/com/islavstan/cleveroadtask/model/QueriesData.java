package com.islavstan.cleveroadtask.model;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Query;


public class QueriesData {
    @SerializedName("title")
    String title;
    @SerializedName("pagemap")
    Pagemap pagemap;

    String imagePath;

    private boolean selected;

    int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public Pagemap getPagemap() {
        return pagemap;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
