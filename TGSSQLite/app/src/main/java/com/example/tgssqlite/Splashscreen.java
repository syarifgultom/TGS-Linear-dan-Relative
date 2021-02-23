package com.example.tgssqlite;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.widget.TextView;
import android.os.Handler;

public class Splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //menghilangkan ActionBar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 2000L); //3000 L = 3 detik
    }
}
