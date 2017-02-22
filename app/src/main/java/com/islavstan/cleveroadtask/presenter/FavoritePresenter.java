package com.islavstan.cleveroadtask.presenter;

import com.islavstan.cleveroadtask.adapters.MyFavoriteRecAdapter;
import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.squareup.picasso.Picasso;



public interface FavoritePresenter {

    void loadData(MyFavoriteRecAdapter adapter, String searchRequest, DBMethods dbMethods);

    void openImage();

    void deleteFromDb(int id,  DBMethods db, int position, MyFavoriteRecAdapter adapter);

    void loadMore(int index, DBMethods db, MyFavoriteRecAdapter adapter);
}
