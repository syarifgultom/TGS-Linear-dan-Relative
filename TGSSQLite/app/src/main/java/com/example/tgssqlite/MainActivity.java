package com.example.tgssqlite;

import android.os.Bundle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tgssqlite.adapter.Adapter;
import com.example.tgssqlite.helper.DbHelper;
import com.example.tgssqlite.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        ListView listView;
        AlertDialog.Builder dialog;
        List<Data> itemList = new ArrayList<Data>();
        Adapter adapter;
        DbHelper SQLite = new DbHelper(this);

        public static final String TAG_ID = "id";
        public static final String TAG_JUDUL = "judul";
        public static final String TAG_DESCRIPTION = "description";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            SQLite = new DbHelper(getApplicationContext());

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            listView = (ListView) findViewById(R.id.list_view);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, AddEdit.class);
                    startActivity(intent);
                }
            });

            adapter = new Adapter(MainActivity.this, itemList);
            listView.setAdapter(adapter);

            // Untuk tekan lama
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                               final int position, long id) {
                    // TODO Auto-generated method stub
                    final String idx         = itemList.get(position).getId();
                    final String judul       = itemList.get(position).getJudul();
                    final String description = itemList.get(position).getDescription();

                    final CharSequence[] dialogitem = {"Edit", "Delete"};
                    dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setCancelable(true);
                    dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            switch (which) {
                                case 0:
                                    Intent intent = new Intent(MainActivity.this, AddEdit.class);
                                    intent.putExtra(TAG_ID, idx);
                                    intent.putExtra(TAG_JUDUL, judul);
                                    intent.putExtra(TAG_DESCRIPTION, description);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    SQLite.delete(Integer.parseInt(idx));
                                    itemList.clear();
                                    getAllData();
                                    break;
                            }
                        }
                    }).show();
                    return false;
                }
            });

            getAllData();

        }

        private void getAllData() {
            ArrayList<HashMap<String, String>> row = SQLite.getAllData();

            for (int i = 0; i < row.size(); i++) {
                String id = row.get(i).get(TAG_ID);
                String poster = row.get(i).get(TAG_JUDUL);
                String title = row.get(i).get(TAG_DESCRIPTION);

                Data data = new Data();

                data.setId(id);
                data.setJudul(poster);
                data.setDescription(title);

                itemList.add(data);
            }

            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onResume() {
            super.onResume();
            itemList.clear();
            getAllData();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
}