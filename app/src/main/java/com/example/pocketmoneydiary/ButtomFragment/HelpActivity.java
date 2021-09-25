package com.example.pocketmoneydiary.ButtomFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.pocketmoneydiary.R;

public class HelpActivity extends AppCompatActivity {

    TextView tv_help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        tv_help = findViewById(R.id.help);
        tv_help.setMovementMethod(new ScrollingMovementMethod());
    }
}