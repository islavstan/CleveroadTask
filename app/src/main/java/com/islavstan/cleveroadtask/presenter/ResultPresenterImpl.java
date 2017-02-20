package com.islavstan.cleveroadtask.presenter;

import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.fragments.ResultFragment;
import com.islavstan.cleveroadtask.interactor.ResultInteractor;
import com.islavstan.cleveroadtask.interactor.ResultInteractorImpl;


public class ResultPresenterImpl implements ResultPresenter {


    private ResultInteractor resultInteractor;

    public ResultPresenterImpl(ResultFragment resultFragment) {

        resultInteractor = new ResultInteractorImpl();
    }



    @Override
    public void loadData(MyRecyclerViewAdapter adapter, String searchRequest) {
     resultInteractor.loadData(adapter, searchRequest );
    }

    @Override
    public void openImage() {

    }
}
