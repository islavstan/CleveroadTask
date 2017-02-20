package com.islavstan.cleveroadtask.interactor;

import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;

/**
 * Created by islav on 20.02.2017.
 */

public interface ResultInteractor {
    void loadData(MyRecyclerViewAdapter adapter, String searchRequest);
}
