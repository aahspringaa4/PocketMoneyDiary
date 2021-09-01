package com.example.pocketmoneydiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime;
    TextView pocket;
    TextView save;
    TextView tv_get;
    public static TextView SumGet;
    public static TextView SumPost;
    public static TextView SumSave;
    String Post1, Save1, Get1, total;
    public static TextView SumMoney;
    String st_pocket;
    TextView money;
    ImageButton bt_scan, menu;
    ImageButton setting;

    public static Boolean tempBoolean = false;
    public static Boolean tempBoolean1 = false;
    public static Boolean tempBoolean2 = false;
    public static Boolean tempBoolean3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        money = (TextView) findViewById(R.id.money);
        pocket = (TextView) findViewById(R.id.pocket);
        save = (TextView) findViewById(R.id.save);
        bt_scan = (ImageButton) findViewById(R.id.bt_scan);
        menu = (ImageButton) findViewById(R.id.menu);
        tv_get = (TextView) findViewById(R.id.tv_get);
        SumGet = (TextView) findViewById(R.id.SumGet);
        SumPost = (TextView) findViewById(R.id.SumPost);
        SumSave = (TextView) findViewById(R.id.SumSave);
        SumMoney = (TextView) findViewById(R.id.SumMoney);
        setting = (ImageButton) findViewById(R.id.btn_7);

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

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        
        SharedPreferences pref = getSharedPreferences("save1", 0);
        Get1 = pref.getString("get", String.valueOf(0));
        Post1 = pref.getString("post", String.valueOf(0));
        Save1 = pref.getString("save", String.valueOf(0));
        total = pref.getString("total", String.valueOf(0));
        SumGet.setText(Get1);
        SumPost.setText(Post1);
        SumSave.setText(Save1);
        SumMoney.setText(total);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (tempBoolean3){
            MainActivity.SumGet.setText("0");
            MainActivity.SumPost.setText("0");
            MainActivity.SumSave.setText("0");
            MainActivity.SumMoney.setText("0");
            tempBoolean3 = false;
        }

        if (tempBoolean) {
            if (NumberPadActivity.a > 0) {
                SumGet.setText(String.valueOf(Integer.valueOf((String) SumGet.getText()) + Integer.valueOf((String) NumberPadActivity.str)));
                tempBoolean = false;
            }
        }
        if (tempBoolean1) {
            if (SaveActivity.a > 0) {
                SumSave.setText(String.valueOf(Integer.valueOf((String) SumSave.getText()) + Integer.valueOf((String) SaveActivity.str)));
                tempBoolean1 = false;
            }
        }

        if (tempBoolean2) {
            if (ScanActivity.a > 0) {
                SumPost.setText(String.valueOf(Integer.valueOf((String) SumPost.getText()) + Integer.valueOf((String) ScanActivity.str)));
                tempBoolean2 = false;
            }
        }

        SumMoney.setText(String.valueOf(Integer.valueOf(String.valueOf(SumGet.getText())) - Integer.valueOf(String.valueOf(SumPost.getText()))));
    }

    @Override
    protected void onStop() {

        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("save1", 0);
        SharedPreferences.Editor pref = sharedPreferences.edit();
        String get = SumGet.getText().toString();
        String post = SumPost.getText().toString();
        String save = SumSave.getText().toString();
        String total = SumMoney.getText().toString();
        pref.putString("get", get);
        pref.putString("post", post);
        pref.putString("save", save);
        pref.putString("total", total);
        pref.commit();
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            finish();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}