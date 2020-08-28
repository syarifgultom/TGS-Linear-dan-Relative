package com.example.belajarintent_lat2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button explicitintent;
    Button implicitintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explicitintent = (Button)findViewById(R.id.btn1);
        explicitintent.setOnClickListener(this);
        implicitintent = (Button)findViewById(R.id.btn2);
        implicitintent.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                Intent explicit = new Intent(MainActivity.this, IntentActivity.class);
                startActivity(explicit);
                break;
            case R.id.btn2:
                Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://id.pinterest.com/pin/612419249313526129/"));
                startActivity(implicit);
                break;
            default:
                break;
        }
    }

}