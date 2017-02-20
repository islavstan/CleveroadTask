package com.islavstan.cleveroadtask.presenter;


import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;

public interface ResultPresenter {
    void loadData(MyRecyclerViewAdapter adapter, String searchRequest);

    void openImage();
}
