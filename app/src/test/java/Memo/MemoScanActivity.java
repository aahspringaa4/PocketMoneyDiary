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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MemoScanActivity extends AppCompatActivity {
    TextView topic;
    ImageButton back;
    private int Request_Re = 202;
    public static Button nowaday;
    Button save;
    public static int a = 0;
    public static EditText memo;
    public static String str, stt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_scan);

        back = (ImageButton) findViewById(R.id.back);
        topic = (TextView) findViewById(R.id.textView2);
        nowaday = (Button) findViewById(R.id.nowaday);
        save = (Button) findViewById(R.id.save);
        memo = (EditText) findViewById(R.id.memo);
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
                                nowaday.setText(String.valueOf(year) + "년 " + String.valueOf(monthOfYear + 1) + "월 " + String.valueOf(dayOfMonth) + "일");
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
                a = 0;
                if (memo.getText().length() > 0 && nowaday.getText().length() > 0) {
                    String str = nowaday.getText().toString();
                    String stt = memo.getText().toString();
                    MemoActivity.post();
                    a++;
                    finish();
                } else {
                    Toast.makeText(MemoScanActivity.this, "날짜 또는 메모 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}