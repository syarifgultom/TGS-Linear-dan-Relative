package com.example.sql_lite_syarif;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.sql_lite_syarif.DatabaseHelper;

public class DatabaseManager {
    public Cursor fetch(){
        private DatabaseHelper dbHelper;
        String[] columns    = new String[] {dbHelper._ID, dbHelper.TITLE, dbHelper.DESC};
        Cursor cursor       = database.query{dbHelper.TABLE_NAME, columns, selection: null, selectionArgs: null}
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
}
