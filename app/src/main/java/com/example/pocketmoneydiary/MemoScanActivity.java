package com.example.pocketmoneydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MemoScanActivity extends AppCompatActivity {
    TextView topic;
    ImageButton back;
    public static Button nowaday;
    Button save;
    public static EditText memo;
    PreferenceManager pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_scan);

        back = (ImageButton)findViewById(R.id.back);
        topic = (TextView)findViewById(R.id.textView2);
        nowaday = (Button)findViewById(R.id.nowaday);
        save = (Button)findViewById(R.id.save);
        memo = (EditText)findViewById(R.id.memo);
        back.bringToFront();
        topic.bringToFront();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nowaday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int nYear = c.get(Calendar.YEAR);
                int nMon = c.get(Calendar.MONTH);
                int nDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener mDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                nowaday.setText(String.valueOf(year) + "년 " + String.valueOf(monthOfYear + 1)+ "월 " + String.valueOf(dayOfMonth )+ "일");
                            }
                        };

                DatePickerDialog oDialog = new DatePickerDialog(MemoScanActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog,
                        mDateSetListener, nYear, nMon, nDay);
                oDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(memo.getText().length()>0 && nowaday.getText().length() > 0){
                    // 저장 버튼을 눌러
                    // 작성한 editText를 저장
                    String edit_title = nowaday.getText().toString();
                    String edit_content = memo.getText().toString();
                    // String 값을 JSONObject로 변환하여 사용할 수 있도록 메모의 제목과 타이틀을 JSON 형식로 저장
                    String save_form = "{\"title\":\""+edit_title+"\",\"content\":\""+edit_content+"\"}";

                    // key값이 겹치지 않도록 현재 시간으로 부여
                    long now = System.currentTimeMillis();
                    Date mDate = new Date(now);
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String getTime = simpleDate.format(mDate).toString();

                    Log.d("WriteActivity","제목 : "+edit_title+", 내용 : "+edit_content+", 현재시간 : "+getTime);
                    //PreferenceManager 클래스에서 저장에 관한 메소드를 관리
                    pref.setString(getApplication(),getTime,save_form);

                    // Intent로 값을 MainActivity에 전달
                    Intent intent = new Intent();
                    intent.putExtra("get",getTime);
                    intent.putExtra("memo2",edit_title);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(MemoScanActivity.this, "날짜 또는 메모 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("save2", 0);
        SharedPreferences.Editor pref1 = sharedPreferences.edit();
        String day = nowaday.getText().toString();
        String memo4 = memo.getText().toString();

        pref1.putString("get", day);
        pref1.putString("memo2", memo4);
    }
}