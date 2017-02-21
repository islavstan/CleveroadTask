package com.islavstan.cleveroadtask.presenter;


import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.squareup.picasso.Picasso;

public interface ResultPresenter {
    void loadData(MyRecyclerViewAdapter adapter, String searchRequest);

    void openImage();

    void saveToDB(QueriesData data, DBMethods db, Picasso picasso);

    void deleteFromDb(QueriesData data,  DBMethods db);
}
