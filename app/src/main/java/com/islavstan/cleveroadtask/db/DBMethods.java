package com.islavstan.cleveroadtask.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;



public class DBMethods {
    DBHelper dbHelper;
    SQLiteDatabase db;
    Context context;

    public DBMethods(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
}
