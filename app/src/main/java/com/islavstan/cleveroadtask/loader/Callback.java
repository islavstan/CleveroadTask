package com.islavstan.cleveroadtask.loader;



public interface Callback<D> {

    void onFailure(Exception ex);

    void onSuccess(D result);
}