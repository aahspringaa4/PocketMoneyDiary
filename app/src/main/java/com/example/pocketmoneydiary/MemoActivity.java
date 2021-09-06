package com.example.pocketmoneydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MemoActivity extends AppCompatActivity {

    TextView topic;
    ImageButton back, go;

    private ArrayList<MemoData> arrayList; // MaindData를 담은 배열 리스트인 arrayList를 선언
    private MemoAdapter memoAdapter; // 인스턴스 mainAdapter를 선언
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager; // 레이아웃 관리자 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager); // recyclerView를 통해 리사이클러뷰를 지정
        memoAdapter = new MemoAdapter(arrayList); // 인스턴스 생성
        recyclerView.setAdapter(memoAdapter); // 어댑터를 mainAdapter로 설정
        arrayList = new ArrayList<>();

        go = (ImageButton)findViewById(R.id.go);
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

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemoActivity.this, MemoScanActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemadd();
    }

    private void itemadd() {
        MemoData MemoData;

        memoAdapter.notifyDataSetChanged(); // 새로고침
    }
}