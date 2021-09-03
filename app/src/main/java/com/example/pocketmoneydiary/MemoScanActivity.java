package com.example.pocketmoneydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MemoScanActivity extends AppCompatActivity {
    TextView topic;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_scan);

        back = (ImageButton)findViewById(R.id.back);
        topic = (TextView)findViewById(R.id.textView2);
        back.bringToFront();
        topic.bringToFront();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}