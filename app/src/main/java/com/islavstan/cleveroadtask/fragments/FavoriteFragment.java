package com.islavstan.cleveroadtask.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.islavstan.cleveroadtask.R;
import com.islavstan.cleveroadtask.adapters.MyFavoriteRecAdapter;
import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.db.DBMethods;
import com.islavstan.cleveroadtask.listeners.UserActionsListener;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.islavstan.cleveroadtask.presenter.FavoritePresenter;
import com.islavstan.cleveroadtask.presenter.FavoritePresenterImpl;
import com.islavstan.cleveroadtask.presenter.ResultPresenter;
import com.islavstan.cleveroadtask.view.FragmentView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment  extends Fragment implements FragmentView {
    List<QueriesData> queriesDataList = new ArrayList<>();
    RecyclerView recyclerView;
    MyFavoriteRecAdapter adapter;
    FavoritePresenter presenter;
    DBMethods db;
    LinearLayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);
        presenter = new FavoritePresenterImpl(this);
        db = new DBMethods(getActivity());
        loadUI(v);
        loadData();
        return v;

    }

    public void update(String textSearch) {
        if (textSearch.equals("")) {
            loadData();
        }
        adapter.onLoad();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void loadUI(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        adapter = new MyFavoriteRecAdapter(queriesDataList, listener);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setLoadMoreListener(new MyRecyclerViewAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        int index = queriesDataList.size() - 1;
                        if (index >= 10)
                            loadMore(index);
                    }
                });
            }
        });


    }

    private void loadMore(int index) {

        presenter.loadMore(index, db, adapter);
    }


    UserActionsListener listener = new UserActionsListener() {
        @Override
        public void openImage(QueriesData data) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            PhotoFragment fragment = new PhotoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("srcFromDB", data.getImagePath());
            fragment.setArguments(bundle);
            transaction.replace(R.id.container, fragment, "photo");
            transaction.addToBackStack(null);
            transaction.commit();
        }

        @Override
        public void saveToDB(QueriesData data) {


        }

        @Override
        public void deleteFromDB(int id, int position) {
            presenter.deleteFromDb(id, db, position, adapter);

        }
    };

    @Override
    public void loadData() {

        presenter.loadData(adapter, "", db);
    }

    @Override
    public void showSuccessSaveToast() {

    }

    @Override
    public void showSuccessDeleteToast() {

    }


}