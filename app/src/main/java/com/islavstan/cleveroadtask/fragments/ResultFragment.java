package com.islavstan.cleveroadtask.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.islavstan.cleveroadtask.R;
import com.islavstan.cleveroadtask.listeners.UserActionsListener;
import com.islavstan.cleveroadtask.adapters.MyRecyclerViewAdapter;
import com.islavstan.cleveroadtask.model.QueriesData;
import com.islavstan.cleveroadtask.presenter.ResultPresenter;
import com.islavstan.cleveroadtask.presenter.ResultPresenterImpl;
import com.islavstan.cleveroadtask.view.FragmentView;

import java.util.ArrayList;
import java.util.List;


public class ResultFragment extends Fragment implements FragmentView {
    List<QueriesData> queriesDataList = new ArrayList<>();
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    ResultPresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result, container, false);
        presenter = new ResultPresenterImpl(this);
        loadUI(v);
        loadData();
        return v;

    }

    @Override
    public void loadUI(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        adapter = new MyRecyclerViewAdapter(queriesDataList, listener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    UserActionsListener listener = new UserActionsListener() {
        @Override
        public void openImage() {
            Toast.makeText(getActivity(), "press", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void loadData() {
        presenter.loadData(adapter,"");
    }

    public void update(String textSearch) {
        presenter.loadData(adapter,textSearch);

    }
}
