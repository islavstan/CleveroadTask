package com.islavstan.cleveroadtask.presenter;


import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.model.Queries;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.squareup.picasso.Picasso;

public interface ResultPresenter {

    void saveToDB(QueriesData data, DBMethods db, Picasso picasso);

    void deleteFromDb(int id, DBMethods db);

    void loadData(MyRecyclerViewAdapter adapter, Queries queries);
}
