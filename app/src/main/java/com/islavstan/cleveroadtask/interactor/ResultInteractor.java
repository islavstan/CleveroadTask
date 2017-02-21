package com.islavstan.cleveroadtask.interactor;

import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.squareup.picasso.Picasso;



public interface ResultInteractor {


    interface ResultFinishedListener {
        void onSuccessSaveToDB();

        void onSuccessDeleteFromDB();
    }

    void loadData(MyRecyclerViewAdapter adapter, String searchRequest);
    void saveToDB(QueriesData data, DBMethods db, Picasso picasso, ResultFinishedListener listener);
    void deleteFromDb(QueriesData data,  DBMethods db, ResultFinishedListener listener);
}
