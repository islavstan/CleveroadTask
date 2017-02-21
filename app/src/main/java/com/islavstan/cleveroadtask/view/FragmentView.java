package com.islavstan.cleveroadtask.view;


import android.view.View;

public interface FragmentView {
    void loadUI(View v);

    void loadData();

    void showSuccessSaveToast();

    void showSuccessDeleteToast();
}
