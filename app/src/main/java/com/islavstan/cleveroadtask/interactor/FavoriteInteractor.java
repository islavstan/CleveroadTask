package com.islavstan.cleveroadtask.interactor;

import com.islavstan.cleveroadtask.adapters.MyFavoriteRecAdapter;
import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.squareup.picasso.Picasso;

/**
 * Created by islav on 21.02.2017.
 */

public interface FavoriteInteractor {

    interface FavoriteFinishedListener {

        void onSuccessDeleteFromDB();
    }

    void loadData(MyFavoriteRecAdapter adapter, String searchRequest, DBMethods db);

    void deleteFromDb(QueriesData data, DBMethods db, FavoriteFinishedListener listener);
}

