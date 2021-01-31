package com.example.sql_lite_syarif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.database.Cursor;
import android.app.Service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    private DatabaseHelper dbHelper;

    final String[] from = new String[]{dbHelper._ID, dbHelper.TITLE, dbHelper.DESC};
    final int[] to      = new int[]{R.id.id, R.id.listTitle, R.id.listDesc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbManager = new DatabaseManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.myListView);

        adapter = new SimpleCursorAdapter(this, R.layout.adapter, cursor, from, to, flags: 0);
        listView.setAdapter(adapter);
        try {
            Intent intent = getIntent();
            Boolean ItemDeleted = intent.getExtras().getBoolean( "ItemDeleted");
            ModifyActivity modifyActivity = new ModifyActivity();
            if(ItemDeleted){
                Snackbar.make(listView, "ItemDeleted!", Snackbar.LENGTH_LONG).show();
                modifyActivity.setItemDeleted(false);
            }
        } catch (Exception e){
            if(adapter.isEmpty()){
                Snackbar.make(listView, "Click on fab to add list", Snackbar.LENGTH_LONG).show();
            } else{
                Snackbar.make(listView, "Hold on item to mofify", Snackbar.LENGTH_LONG).show();
            }
        }

        listView.setOnItemClickListener((parent, view, position, id) ->{

            TextView itemID     = (TextView) view.findViewById(R.id.id);
            TextView itemTitle  = (TextView) view.findViewById(R.id.ListTitle);
            TextView itemDesc   = (TextView) view.findViewById(R.id.ListDesc);

            String myId         = itemID.getText().toString();
            String myTitle      = itemTitle.getText().toString();
            String myDesc       = itemDesc.getText().toString();

            Intent intent       = new Intent(getApplicationContext(), ModifyActivity.class);
            intent.putExtra("Id", myId);
            intent.putExtra("Id", myTitle);
            intent.putExtra("Id", myDesc);
            startActivity(intent);

            return false;
        });
    }
    public void onClickAddItem(View view) {
        Intent intent = new Intent(getApplicationContext(), AddItem.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_clear_all){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}