package com.example.pocketmoneydiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime;
    TextView pocket, save, tv_get;
    String st_pocket;
    TextView money;
    ImageButton bt_scan, menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        money = (TextView)findViewById(R.id.money);
        pocket = (TextView)findViewById(R.id.pocket);
        save = (TextView)findViewById(R.id.save);
        bt_scan = (ImageButton) findViewById(R.id.bt_scan);
        menu = (ImageButton) findViewById(R.id.menu);
        tv_get = (TextView) findViewById(R.id.tv_get);
        money.bringToFront();
        pocket.bringToFront();
        save.bringToFront();

        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });

        pocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NumberPadActivity.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(tv_get.getText().toString().equals("")) {
//            Intent intent = getIntent();
//            String str = intent.getStringExtra("str");
//            tv_get.setText("     " + str);
//            Log.d("결과", "성공1");
//        }
//        else {
//            Intent intent = getIntent();
//            String str = intent.getStringExtra("str");
//            tv_get.setText("     " + String.valueOf(Integer.parseInt((String) tv_get.getText()) + Integer.parseInt(str)));
//        }
    }

    @Override
    public void onBackPressed(){
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            finish();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}