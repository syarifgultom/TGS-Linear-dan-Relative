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

public class ModifyActivity extends AppCompatActivity {

    private EditText modTitle;
    private EditText modDesc;
    private long id;
    private boolean isItemDeleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbManager  = new DatabaseManager(this);
        dbManager.open();

        modTitle = (EditText) findViewById(R.id.modTitle);
        modDesc  = (EditText) findViewById(R.id.modDesc);

        Bundle intentData  = getIntent().getExtras();

        final String myID           = intentData.getString("Id");
        final String myTitle        = intentData.getString("Title");
        final String myDescription  = intentData.getString("Desc");

        modTitle.setText(myTitle);
        modDesc.setText(myDescription);
        id = Long.parseLong(myID);

        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fabDelete);
        FloatingActionButton fabUpdate = (FloatingActionButton) findViewById(R.id.fabUpdate);
        fabDelete.setOnClickListener{
            (v) {
                dbManager.delete(Intent.parseInt(myID));
                setItemDeleted(true);
                returnHome();
            }
        };
        fabUpdate.setOnClickListener{
            (v) {
                String newTitle = modTitle.getText().toString();
                Stirng newDesc  = modDesc.getText().toString();
                dbManager.update(Integer.parseInt(myID), newTitle, newDesc);
                returnHome();
            }
        };
    }

    public void returnHome() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(isItemDeleted){
            intent.putExtra("ItemDeleted", true);
        }
        startActivity(intent);
    }
    public void setItemDeleted(boolean itemDeleted) { isItemDeleted = itemDeleted;}
}