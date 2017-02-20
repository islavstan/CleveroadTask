package com.islavstan.cleveroadtask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by islav on 20.02.2017.
 */
public class Pagemap {
    @SerializedName("cse_thumbnail")
   List<CseThumbnailData> cseThumbnailData;

    public List<CseThumbnailData> getCseThumbnailData() {
        return cseThumbnailData;
    }
}
