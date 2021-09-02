package com.example.pocketmoneydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MemoActivity extends AppCompatActivity {

    TextView topic;
    ImageButton back;
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

        back = (ImageButton)findViewById(R.id.back);
        topic = (TextView)findViewById(R.id.textView2);
        back.bringToFront();
        topic.bringToFront();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemadd();
    }

    private void itemadd() {
        MemoData MemoData;

        MemoData = new MemoData("2021년 9월 02일", "정윤이한테 만원 받아야함");
        arrayList.add(MemoData);
        Log.d("결과", "성공");

        MemoData = new MemoData("2021년 9월 02일", "세현이한테 만원 줘야함");
        arrayList.add(MemoData);

        memoAdapter.notifyDataSetChanged(); // 새로고침
    }
}