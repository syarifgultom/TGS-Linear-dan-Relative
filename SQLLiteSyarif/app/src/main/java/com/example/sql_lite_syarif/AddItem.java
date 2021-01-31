package com.example.sql_lite_syarif;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toolbar;

public class AddItem extends AppCompatActivity {
    private EditText inputTitle;
    private EditText inputDesc;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inputTitle = (EditText) findViewById(R.id.inputItem);
        inputDesc  = (EditText) findViewById(R.id.inputDesc);

        dbManager = new DatabaseManager(this);
        dbManager.open();
    }

    public void onClickDone(View view){
        String myInputTitle = inputTitle.getText().toString();
        String myInputDesc  = inputDesc.getText().toString();
        if(myInputTitle.isEmpty() || myInputDesc.isEmpty()){
            Snackbar.make(view, "Please fill in both form!", Snackbar.LENGTH_SHORT).show();
        } else{
            dbManager.insert(myInputTitle,myInputDesc);
            Intent intent = new Intent( AddItem.this, MainActivity.class).setFlags(Intent startActivity(intent));
        }
    }
}