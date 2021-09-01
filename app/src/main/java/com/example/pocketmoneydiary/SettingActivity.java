package com.example.pocketmoneydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SettingActivity extends AppCompatActivity {

    ImageButton back;
    Button clear;
    public static int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        back = (ImageButton)findViewById(R.id.back);
        clear = (Button)findViewById(R.id.clear);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.tempBoolean3 = true;
            }
        });
    }
}