package com.example.pocketmoneydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    private int REQUEST_TEST = 200;
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
        memoAdapter = new MemoAdapter(arrayList); // 인스턴스
        recyclerView.setAdapter(memoAdapter); // 어댑터를 mainAdapter로
        arrayList = new ArrayList<>();

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
                //startActivity(intent); // 해당 액티비티에 단순 값 전달과 이동이면 startActivity
                startActivityForResult(intent, REQUEST_TEST); // 메모를 작성하고 결과값을 받기 위해
            }
        });

        SharedPreferences prefb = getSharedPreferences("save2", MODE_PRIVATE);
        Collection<?> col_val = prefb.getAll().values();
        Iterator<?> it_val = col_val.iterator();
        Collection<?> col_key = prefb.getAll().keySet();
        Iterator<?> it_key = col_key.iterator();

        while (it_val.hasNext() && it_key.hasNext()) {
            String key = (String) it_key.next();
            String value = (String) it_val.next();

            // value 값은 다음과 같이 저장되어있다
            // "{\"title\":\"hi title\",\"content\":\"hi content\"}"
            try {
                // String으로 된 value를 JSONObject로 변환하여 key-value로 데이터 추출
                JSONObject jsonObject = new JSONObject(value);
                String day = (String) jsonObject.getString("get");
                String content = (String) jsonObject.getString("memo2");
                // 리사이클러뷰 어뎁터 addItem으로 목록 추가
                memoAdapter.addItem(new MemoData(day, content));
            } catch (JSONException e) {

            }

            // 목록 갱신하여 뷰에 띄어줌
            memoAdapter.notifyDataSetChanged();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TEST) {
            if (resultCode == RESULT_OK) {
                // 전달 받은 값
                Intent intent = getIntent();
                String get_Tv_today = data.getStringExtra("get");
                String get_content = data.getStringExtra("memo2");
                // 리사이클러뷰 목록에 추가
                memoAdapter.addItem(new MemoData(get_Tv_today,get_content));
                // 목록 갱신
                memoAdapter.notifyDataSetChanged();
                Toast.makeText(MemoActivity.this, "메모 작성 되었습니다", Toast.LENGTH_SHORT).show();
            } else {   // RESULT_CANCEL
                Toast.makeText(MemoActivity.this, "취소", Toast.LENGTH_SHORT).show();
            }

        }
    }
}