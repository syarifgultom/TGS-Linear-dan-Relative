package com.example.intent_passingdata;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity_b extends AppCompatActivity implements View.OnClickListener {
    public Button btnMoveActivity;
    public Button btnMoveWithDataActivity;
    public Button btnMoveWithObject;
    Button btnDialNumber;
    Button btnMoveResult;
    TextView tvResult;

    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);
        btnMoveActivity = (Button)findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        btnMoveWithDataActivity =
                (Button)findViewById(R.id.btn_move_with_data_activity);
        btnMoveWithDataActivity.setOnClickListener(this);

        btnMoveWithObject =
                (Button)findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        btnDialNumber = findViewById(R.id.btn_dial_number);
        btnDialNumber.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);
    }
    @Override
    public void onClick(View v){
        switch (v.getId() ){
            case R.id.btn_move_activity:
                Intent moveIntent = new Intent(MainActivity_b.this,MoveActivity.class);
                startActivity(moveIntent);
                break;

            case R.id.btn_move_with_data_activity:
                Intent movewithDataIntent = new Intent(MainActivity_b.this,MoveWithDataActivity.class);
                movewithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME,"Syarif Saurma Gultom");
                movewithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE,17);
                startActivity(movewithDataIntent);
                break;

            case R.id.btn_move_activity_object:
                Person mPerson = new Person();
                mPerson.setName("Syarif Saurma Gultom");
                mPerson.setAge(17);
                mPerson.setEmail("syarifgultom02@gmail.com");;
                mPerson.setCity("Bandung");
                Intent moveWithObjectIntent = new Intent(MainActivity_b.this,MoveWithObjectActivity.class);
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, mPerson);
                startActivity(moveWithObjectIntent);
                break;

            case R.id.btn_dial_number:
                String phoneNumber = "081320005968";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ phoneNumber));
                startActivity(dialPhoneIntent);
                break;
            case R.id.btn_move_for_result:
                Intent moveForResultIntent = new Intent(MainActivity_b.this,MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if (requestCode == REQUEST_CODE){
            if(resultCode == MoveForResultActivity.RESULT_CODE){
                int selectedValue= data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s", selectedValue));
            }
        }
    }
}
