package com.example.tgssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tgssqlite.helper.DbHelper;

import static com.example.tgssqlite.R.layout.activity_add_edit;

public class AddEdit extends AppCompatActivity {

    EditText txt_id, txt_judul, txt_description;
    Button btn_submit, btn_cancel;
    DbHelper SQLite = new DbHelper(this);
    String id, judul, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_id          = (EditText) findViewById(R.id.txt_id);
        txt_judul       = (EditText) findViewById(R.id.txt_judul);
        txt_description = (EditText) findViewById(R.id.txt_description);
        btn_submit      = (Button) findViewById(R.id.btn_submit);
        btn_cancel      = (Button) findViewById(R.id.btn_cancel);

        id          = getIntent().getStringExtra(MainActivity.TAG_ID);
        judul       = getIntent().getStringExtra(MainActivity.TAG_JUDUL);
        description = getIntent().getStringExtra(MainActivity.TAG_DESCRIPTION);

        if (id == null || id == "") {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_judul.setText(judul);
            txt_description.setText(description);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Make blank all Edit Text
    private void blank() {
        txt_judul.requestFocus();
        txt_id.setText(null);
        txt_judul.setText(null);
        txt_description.setText(null);
    }

    // Buat save
    private void save() {
        if (String.valueOf(txt_judul.getText()).equals(null) || String.valueOf(txt_judul.getText()).equals("") ||
                String.valueOf(txt_description.getText()).equals(null) || String.valueOf(txt_description.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Harap masukkan Judul atau Deskripsi ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_judul.getText().toString().trim(), txt_description.getText().toString().trim());
            blank();
            finish();
        }
    }

    // Buat update
    private void edit() {
        if (String.valueOf(txt_judul.getText()).equals(null) || String.valueOf(txt_description.getText()).equals("") ||
                String.valueOf(txt_description.getText()).equals(null) || String.valueOf(txt_description.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_judul.getText().toString().trim(),
                    txt_description.getText().toString().trim());
            blank();
            finish();
        }
    }
}