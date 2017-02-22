package com.islavstan.cleveroadtask.interactor;

import com.islavstan.cleveroadtask.adapters.MyFavoriteRecAdapter;
import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.squareup.picasso.Picasso;


public interface FavoriteInteractor {

    interface FavoriteFinishedListener {

        void onSuccessDeleteFromDB();
    }

    void loadData(MyFavoriteRecAdapter adapter, String searchRequest, DBMethods db);

    void addData(MyFavoriteRecAdapter adapter, DBMethods db, int index);

    void deleteFromDb(int id, DBMethods db, FavoriteFinishedListener listener, int position, MyFavoriteRecAdapter adapter);
}

