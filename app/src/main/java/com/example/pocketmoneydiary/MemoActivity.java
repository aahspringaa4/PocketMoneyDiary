package com.example.pocketmoneydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MemoActivity extends AppCompatActivity {

    TextView topic;
    ImageButton back, go;
    private ArrayList<String> arrayList; // MaindData를 담은 배열 리스트인 arrayList를 선언
    private static MemoAdapter adapter; // 인스턴스 mainAdapter를 선언
    private RecyclerView recyclerView;
    private LinearLayoutManager LinearLayoutManager; // 레이아웃 관리자 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager); // recyclerView를 통해 리사이클러뷰를 지정
        adapter = new MemoAdapter(arrayList); // 인스턴스
        recyclerView.setAdapter(adapter); // 어댑터를 mainAdapter로
        arrayList = new ArrayList<String>();

        go = (ImageButton) findViewById(R.id.go);
        back = (ImageButton) findViewById(R.id.back);
        topic = (TextView) findViewById(R.id.textView2);
        back.bringToFront();
        topic.bringToFront();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemoScanActivity.class);
                startActivity(intent);
            }
        });


    }
}