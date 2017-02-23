package com.islavstan.cleveroadtask.listeners;



import com.islavstan.cleveroadtask.model.QueriesData;

public interface UserActionsListener {
    void openImage(QueriesData data);

    void saveToDB(QueriesData data);

    void deleteFromDB(int id, int position);
}

