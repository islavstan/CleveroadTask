package com.islavstan.cleveroadtask.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.islavstan.cleveroadtask.model.QueriesData;

import java.util.ArrayList;
import java.util.List;


public class DBMethods {
    DBHelper dbHelper;
    SQLiteDatabase db;
    Context context;

    public DBMethods(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void saveContent(String text, String photoPath) {
        ContentValues newValues = new ContentValues();
        newValues.put("name", text);
        newValues.put("photo_path", photoPath);
        db.insert("Favorite", null, newValues);
    }

    public void delete(int id) {
        db.delete("Favorite", "id = " + id, null);
    }


    public List<QueriesData> getContentFromDB(int indexStart) {
        List<QueriesData> contentList = new ArrayList();

        String query = "SELECT  * FROM Favorite LIMIT " + indexStart + ", 10";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                QueriesData data = new QueriesData();
                data.setTitle(cursor.getString(cursor.getColumnIndex("name")));
                data.setImagePath(cursor.getString(cursor.getColumnIndex("photo_path")));
                data.setId(cursor.getInt(cursor.getColumnIndex("id")));
                data.setSelected(true);
                contentList.add(data);
            }
            while (cursor.moveToNext());
        }
        return contentList;
    }

    public int getCount() {
        String countQuery = "SELECT  * FROM Favorite";
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}
